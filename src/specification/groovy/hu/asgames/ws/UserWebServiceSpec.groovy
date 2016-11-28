package hu.asgames.ws

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
    savedUser.registrationState == 'NEW'
    !savedUser.registrationCode.isEmpty()
    and: "given data are stored."
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

  @Unroll
  def "Login works"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "her login data."
    LoginRequest loginData = new LoginRequest().with {
      username = loginUsername
      password = loginPassword
      return it
    }
    when: "we try login"
    Long userId = userService.login(loginData)
    then: "the success comes with our expectations"
    (userId == user.id) == loginSucceed

    where: "we take different login combinations."
    loginPassword     | loginUsername     | loginSucceed | testCase
    ORIGINAL_PASSWORD | ORIGINAL_USERNAME | true         | "Login with correct data"
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

  @Unroll
  def "Change password works and uses credentials properly"() {
    given: "a user"
    UserVo user = userService.getUser(USER_ID)
    and: "a request of password change."
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = loginPassword
      newPassword = MODIFIED_PASSWORD
      return it
    }
    when: "we send request"
    userService.changePassword(user.id, request)
    then: "password is changed only with correct credentials"
    LoginRequest loginData = new LoginRequest().with {
      username = MODIFIED_USERNAME // Note we use the modified username because of the user modification.
      password = MODIFIED_PASSWORD
      return it
    }
    Long userId = userService.login(loginData)
    (userId != null) == changeSucceed
    where: "we take different password combinations."
    // Test case order is important! After a successful change the checking login always will succeed.
    loginPassword     | changeSucceed | testCase
    WRONG_PASSWORD    | false         | "Change password with wrong old password"
    ORIGINAL_PASSWORD | true          | "Change password with correct data"
  }

  def "User deletion succeeds"() {
    given: "a user."
    UserVo user = userService.getUser(USER_ID)
    when: "we delete this user"
    userService.deleteUser(user.id)
    then: "the deletion is performed."
    UserVo deletedUser = userService.getUser(user.id)
    deletedUser.userState == 'DELETED'
  }
}