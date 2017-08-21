package hu.asgames.ws

import hu.asgames.domain.enums.RegistrationState
import hu.asgames.domain.enums.UserState
import hu.asgames.report.Report
import hu.asgames.ws.api.domain.user.*
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Unroll

/**
 * @author AMiklo on 2016.10.20.
 */
@Report
@Stepwise
class UserWebServiceSpec extends WebServiceSpecFixtures {

  private static final String ORIGINAL_USERNAME = dummy() // Username must be unique so we take a random dummy value.
  private static final String MODIFIED_USERNAME = dummy()
  private static final String WRONG_USERNAME = 'Gollum'
  private static final String ORIGINAL_PASSWORD = 'myprecious'
  private static final String MODIFIED_PASSWORD = 'smellyfish'
  private static final String WRONG_PASSWORD = 'sting'
  private static final String ORIGINAL_DISPLAY_NAME = 'John Doe'
  private static final String MODIFIED_DISPLAY_NAME = 'Jane Doe'
  private static final String ORIGINAL_EMAIL = 'johndoe@asgames.hu'
  private static final String MODIFIED_EMAIL = 'janedoe@asgames.hu'

  @Shared
  private Long USER_ID = null //Store value between test cases for use of the same user.

  def "User creation succeeds"() {
    given: "a new user."
    CreateUserRequest newUser = new CreateUserRequest().with {
      displayName = ORIGINAL_DISPLAY_NAME
      username = ORIGINAL_USERNAME
      email = ORIGINAL_EMAIL
      password = ORIGINAL_PASSWORD
      return it
    }
    when: "we save the user"
    USER_ID = userService.createUser(newUser)
    then: "we can get it by id"
    UserVo savedUser = userService.getUser(USER_ID)
    and: "it has a TEMPORARY status"
    savedUser.userState == UserState.TEMPORARY.name()
    and: "a valid registration"
    savedUser.registrationState == RegistrationState.NEW.name()
    !savedUser.registrationCode.isEmpty()
    and: "given data is stored."
    savedUser.displayName == ORIGINAL_DISPLAY_NAME
    savedUser.username == ORIGINAL_USERNAME
    savedUser.email == ORIGINAL_EMAIL
    // Password is encoded and cannot be requested.
  }

  def "User list contains created user"() {
    when: "we get the list of users"
    List<UserVo> userList = userService.getUserList()
    then: "there is at least one user"
    userList.size() >= 1
    and: "the user we created before is in the list."
    userList.find({ it.username == ORIGINAL_USERNAME }) != null
  }

  def "User registration can be confirmed"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "this user has a non-confirmed registration."
    assert user.registrationState == RegistrationState.NEW.name()
    when: "we send a confirmation with the registration code"
    userService.registration(user.id, user.registrationCode)
    then: "the registration is finalized"
    UserVo confirmedUser = userService.getUser(user.id)
    confirmedUser.registrationState == RegistrationState.CONFIRMED.name()
    and: "the user gets to NORMAL status."
    confirmedUser.userState == UserState.NORMAL.name()
  }

  def "Confirmed registration cannot be confirmed again"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "this user has an already confirmed registration."
    assert user.registrationState == RegistrationState.CONFIRMED.name()
    when: "we send a confirmation with the registration code"
    userService.registration(user.id, user.registrationCode)
    then: "we get an error."
    AssertionError error = thrown(AssertionError)
    hasErrorResponse(error)
  }

  def "Login works with proper credentials"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "her correct login data."
    LoginRequest loginData = new LoginRequest().with {
      username = ORIGINAL_USERNAME
      password = ORIGINAL_PASSWORD
      return it
    }
    when: "we try login"
    Long userId = userService.login(loginData)
    then: "we get the user's identifier."
    userId == user.id
  }

  @Unroll
  def "Login fails with incorrect credentials"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "a set of incorrect login data."
    LoginRequest loginData = new LoginRequest().with {
      username = loginUsername
      password = loginPassword
      return it
    }
    when: "we try login"
    Long userId = userService.login(loginData)
    then: "we get an error."
    AssertionError error = thrown(AssertionError)
    hasErrorResponse(error)

    where: "we take different login combinations."
    loginPassword     | loginUsername     | loginSucceed | testCase
    ORIGINAL_PASSWORD | WRONG_USERNAME    | false        | "Login with wrong username"
    WRONG_PASSWORD    | ORIGINAL_USERNAME | false        | "Login with wrong password"
    WRONG_PASSWORD    | WRONG_USERNAME    | false        | "Login with wrong username and password"
  }

  def "User modification succeeds"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "some changes on her user data."
    ModifyUserRequest modification = new ModifyUserRequest().with {
      displayName = MODIFIED_DISPLAY_NAME
      username = MODIFIED_USERNAME
      email = MODIFIED_EMAIL
      return it
    }
    when: "we save modifications"
    userService.modifyUser(user.id, modification)
    then: "the changes are executed."
    UserVo modifiedUser = userService.getUser(user.id)
    modifiedUser.displayName == MODIFIED_DISPLAY_NAME
    modifiedUser.username == MODIFIED_USERNAME
    modifiedUser.email == MODIFIED_EMAIL
  }

  def "Change password works with proper credentials"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "a request of password change given the correct old password."
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = ORIGINAL_PASSWORD
      newPassword = MODIFIED_PASSWORD
      return it
    }
    when: "we send request"
    userService.changePassword(user.id, request)
    then: "password is changed."
    LoginRequest loginData = new LoginRequest().with {
      username = MODIFIED_USERNAME // Note we use the modified username because of the user modification.
      password = MODIFIED_PASSWORD
      return it
    }
    userService.login(loginData) == user.id
  }

  def "Change password fails with incorrect credentials"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "a request of password change given an incorrect old password."
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = WRONG_PASSWORD
      newPassword = MODIFIED_PASSWORD
      return it
    }
    when: "we send request"
    userService.changePassword(user.id, request)
    then: "we get an error."
    AssertionError error = thrown(AssertionError)
    hasErrorResponse(error)
  }

  def "User deletion succeeds"() {
    given: "a user."
    UserVo user = userService.getUser(USER_ID)
    when: "we delete this user"
    userService.deleteUser(user.id)
    then: "the deletion is performed."
    UserVo deletedUser = userService.getUser(user.id)
    deletedUser.userState == UserState.DELETED.name()
  }
}
