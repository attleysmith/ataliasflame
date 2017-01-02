package hu.asgames.service.api

import hu.asgames.ReplaceSlf4jLogger
import hu.asgames.dao.UserDao
import hu.asgames.domain.entities.Registration
import hu.asgames.domain.entities.User
import hu.asgames.domain.enums.RegistrationState
import hu.asgames.domain.enums.UserState
import hu.asgames.domain.exception.BaseException
import hu.asgames.messages.MessageBuilder
import hu.asgames.messages.MessageUtil
import hu.asgames.service.impl.UserServiceImpl
import hu.asgames.ws.api.domain.Message
import hu.asgames.ws.api.domain.user.*
import org.junit.Rule
import org.slf4j.Logger
import spock.lang.Specification

/**
 * @author AMiklo on 2016.12.20.
 */
class UserServiceTest extends Specification {

  private static final Long ID = 123456L
  private static final String DISPLAY_NAME = "DISPLAY_NAME"
  private static final String USERNAME = "USERNAME"
  private static final String EMAIL = "EMAIL"
  private static final String PASSWORD = "PASSWORD"
  private static final String ENCODED_PASSWORD = "ENCODED_PASSWORD"
  private static final String REGISTRATION_CODE = "REGISTRATION_CODE"
  private static final String WRONG_REGISTRATION_CODE = "WRONG_REGISTRATION_CODE"
  private static final String NEW_PASSWORD = "NEW_PASSWORD"
  private static final String ENCODED_NEW_PASSWORD = "ENCODED_NEW_PASSWORD"

  private final UserService userService = new UserServiceImpl()
  private AuthenticationService authenticationService = Mock()
  private CodeGeneratorService codeGeneratorService = Mock()
  private UserDao userDao = Mock()
  private Logger logger = Mock()

  @Rule
  ReplaceSlf4jLogger replaceSlf4jLogger = new ReplaceSlf4jLogger(UserServiceImpl, logger)

  void setup() {
    userService.authenticationService = authenticationService
    userService.codeGeneratorService = codeGeneratorService
    userService.userDao = userDao
  }

  def "Getting user list queries all users from database and convert them into value objects as result"() {
    given: "two users (in database)."
    User user1 = createUser(1L)
    User user2 = createUser(2L)
    when: "we get the list of users"
    List<UserVo> userVoList = userService.getUserList()
    then: "all users in database are queried"
    1 * userDao.findAll() >> [user1, user2]
    and: "we find the two users"
    userVoList.size() == 2
    and: "their properties are correctly passed into value objects."
    checkUserProps(userVoList[0], user1)
    checkUserProps(userVoList[1], user2)
  }

  def "User creation saves given data and a new registration, and returns the user identifier"() {
    given: "a user creation request."
    CreateUserRequest request = new CreateUserRequest().with {
      displayName = DISPLAY_NAME
      username = USERNAME
      email = EMAIL
      password = PASSWORD
      return it
    }
    when: "we pass the request to the user creation service"
    Long userId = userService.createUser(request)
    then: "given password is encoded"
    1 * authenticationService.encodePassword(request.password) >> ENCODED_PASSWORD
    and: "a registration code is generated"
    1 * codeGeneratorService.generateRegistrationCode() >> REGISTRATION_CODE
    and: "an info message is logged about the user creation"
    1 * logger.info("User created - {}", request.username)
    and: "the user is saved in the database with correct properties"
    1 * userDao.save({ User user ->
      user.id = ID // giving an identifier to the user while saving
      return user.displayName == request.displayName &&
             user.username == request.username &&
             user.password == ENCODED_PASSWORD &&
             user.email == request.email &&
             user.state == UserState.TEMPORARY &&
             user.registration.user == user &&
             user.registration.state == RegistrationState.NEW &&
             user.registration.registrationDate != null &&
             user.registration.registrationCode == REGISTRATION_CODE
    })
    and: "the user identifier is returned."
    userId == ID
  }

  def "Getting user queries user by id from database and convert it into value object as result"() {
    given: "a user (in database)."
    User user = createUser(ID)
    when: "we get the user by its identifier"
    UserVo userVo = userService.getUser(user.id)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "its properties are correctly passed into the value object."
    checkUserProps(userVo, user)
  }

  def "Getting user by a not existing identifier results in an error"() {
    given: "an error message about not existing user what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    when: "we get the user by a not existing identifier"
    userService.getUser(ID)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findOne(ID) >> null
    and: "the expected error message is logged about the user existence"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message."
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
  }

  def "User modification saves given data as requested"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "a user modification request"
    ModifyUserRequest request = new ModifyUserRequest().with {
      displayName = DISPLAY_NAME
      username = USERNAME
      email = EMAIL
      return it
    }
    and: "the modified properties are not the same as original properties."
    assert user.displayName != request.displayName
    assert user.username != request.username
    assert user.email != request.email
    when: "we pass the request to the user modification service"
    userService.modifyUser(user.id, request)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "an info message is logged about the user modification"
    1 * logger.info("User modified - {}", request.username)
    and: "the user is saved into the database with the modified properties (but others remain unchanged)."
    1 * userDao.save({ User savedUser ->
      savedUser.id == user.id &&
      savedUser.password == user.password &&
      savedUser.state == user.state &&
      savedUser.registration == user.registration &&
      savedUser.displayName == request.displayName &&
      savedUser.username == request.username &&
      savedUser.email == request.email
    })
  }

  def "User modification by a not existing user identifier results in an error"() {
    given: "an error message about not existing user what we expect to be thrown"
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    and: "a user modification request."
    ModifyUserRequest request = new ModifyUserRequest().with {
      displayName = DISPLAY_NAME
      username = USERNAME
      email = EMAIL
      return it
    }
    when: "we pass the request to the user modification service with a not existing user identifier"
    userService.modifyUser(ID, request)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findOne(ID) >> null
    and: "the expected error message is logged about the user existence"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "User deletion changes the state of user to DELETED"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "the user is not deleted yet."
    assert user.state != UserState.DELETED
    when: "we delete the user by its identifier"
    userService.deleteUser(user.id)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "an info message is logged about the user deletion"
    1 * logger.info("User deleted - {}", user.username)
    and: "the user is saved into the database with a deleted state."
    1 * userDao.save({ User savedUser ->
      savedUser.id == user.id &&
      savedUser.state == UserState.DELETED
    })
  }

  def "User deletion fails if the user is already deleted"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "it is already deleted"
    user.state = UserState.DELETED
    and: "there is an error message about already deleted user what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_ALREADY_DELETED).args([userId: user.id as String, username: user.username]).build()
    when: "we delete the user by its identifier"
    userService.deleteUser(user.id)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the expected error message is logged about already deleted user"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "User deletion by a not existing user identifier results in an error"() {
    given: "an error message about not existing user what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    when: "we delete the user by a not existing identifier"
    userService.deleteUser(ID)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findOne(ID) >> null
    and: "the expected error message is logged about the user existence"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Changing password checks old password and saves the encoded new one as requested"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "a password changing request."
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = user.password
      newPassword = NEW_PASSWORD
      return it
    }
    when: "we pass the request to the password changing service"
    userService.changePassword(user.id, request)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the given old password is checked"
    1 * authenticationService.checkPassword(request.oldPassword, user.password) >> true
    and: "the new password is encoded"
    1 * authenticationService.encodePassword(request.newPassword) >> ENCODED_NEW_PASSWORD
    and: "an info message is logged about the change of the password"
    1 * logger.info("User password changed - {}", user.username)
    and: "the user is saved into the database with the new encoded password."
    1 * userDao.save({ User savedUser ->
      savedUser.id == user.id &&
      savedUser.password == ENCODED_NEW_PASSWORD
    })
  }

  def "Changing password with an unsuccessful user authentication results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "a password changing request"
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = user.password
      newPassword = NEW_PASSWORD
      return it
    }
    and: "there is an error message about user authentication what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", user.username).build()
    when: "we pass the request to the password changing service"
    userService.changePassword(user.id, request)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the given old password is checked (reporting failure)"
    1 * authenticationService.checkPassword(request.oldPassword, user.password) >> false
    and: "the expected error message is logged about authentication failure"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String)
    and: "encoding the new password never happened"
    0 * authenticationService.encodePassword(_ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Changing password by a not existing user identifier results in an error"() {
    given: "an error message about not existing user what we expect to be thrown"
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    and: "a password changing request."
    ChangePasswordRequest request = new ChangePasswordRequest().with {
      oldPassword = PASSWORD
      newPassword = NEW_PASSWORD
      return it
    }
    when: "we pass the request to the password changing service with a not existing user identifier"
    userService.changePassword(ID, request)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findOne(ID) >> null
    and: "the expected error message is logged about the user existence"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String)
    and: "the given old password is never checked"
    0 * authenticationService.checkPassword(_ as String, _ as String)
    and: "encoding the new password never happened"
    0 * authenticationService.encodePassword(_ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Successful login request returns the user identifier"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "a login request."
    LoginRequest request = new LoginRequest().with {
      username = USERNAME
      password = PASSWORD
      return it
    }
    when: "we pass the request to the login service"
    Long userId = userService.login(request)
    then: "the user in database is queried"
    1 * userDao.findByUsername(request.username) >> user
    and: "the given password is checked"
    1 * authenticationService.checkPassword(request.password, user.password) >> true
    and: "the user identifier is returned."
    userId == ID
  }

  def "Login request with an unsuccessful user authentication results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "a login request"
    LoginRequest request = new LoginRequest().with {
      username = user.username
      password = PASSWORD
      return it
    }
    and: "there is an error message about user authentication what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", request.username).build()
    when: "we pass the request to the login service"
    userService.login(request)
    then: "the user in database is queried"
    1 * userDao.findByUsername(request.username) >> user
    and: "the given password is checked (reporting failure)"
    1 * authenticationService.checkPassword(request.password, user.password) >> false
    and: "the expected error message is logged about authentication failure"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message."
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
  }

  def "Login request with a not existing username results in an error"() {
    given: "a login request"
    LoginRequest request = new LoginRequest().with {
      username = USERNAME
      password = PASSWORD
      return it
    }
    and: "there is an error message about user authentication what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_AUTH_FAILED).arg("username", request.username).build()
    when: "we pass the request to the login service"
    userService.login(request)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findByUsername(request.username) >> null
    and: "the expected error message is logged about authentication failure"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "the given password is never checked."
    0 * authenticationService.checkPassword(_ as String, _ as String)
  }

  def "Confirming a registration sets states correctly and fills confirmation date while returning user data"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "it has a new registration."
    assert user.registration.state == RegistrationState.NEW
    assert user.registration.confirmationDate == null
    when: "we confirm registration using the registration code"
    userService.registration(user.id, user.registration.registrationCode)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "an info message is logged about the confirmation of the registration"
    1 * logger.info("Registration confirmed - {}", user.registration.registrationCode)
    and: "the user with its registration is saved into the database with correct states and a filled confirmation date."
    1 * userDao.save({ User savedUser ->
      savedUser.id == user.id
      savedUser.state == UserState.NORMAL &&
      savedUser.registration.state == RegistrationState.CONFIRMED &&
      savedUser.registration.confirmationDate != null
    })
  }

  def "Confirming a registration not in a NEW state results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "its registration is not in NEW state"
    user.registration.state = notNewRegistrationState
    and: "there is an error message about wrong registration state what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.CONFIRM_REGISTRATION_WITH_WRONG_STATE).
            args([correctRegistrationState: RegistrationState.NEW.name(), registrationState: user.registration.state.name()]).build()
    when: "we confirm registration using the registration code"
    userService.registration(user.id, user.registration.registrationCode)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the expected error message is logged about wrong registration state"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String, _ as String)
    and: "saving user is never called"
    0 * userDao.save(_ as User)
    where: "we check all not NEW registration state as the user's registration state."
    notNewRegistrationState << [RegistrationState.CONFIRMED, RegistrationState.EXPIRED]
  }

  def "Confirming registration of a not existing user results in an error"() {
    given: "an error message about not existing user what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    when: "we confirm registration using a not existing user identifier"
    userService.registration(ID, REGISTRATION_CODE)
    then: "the user in database is queried (and the query returns an empty result)"
    1 * userDao.findOne(ID) >> null
    and: "the expected error message is logged about the user existence"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String, _ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Confirming a not existing registration results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "it doesn't have any registration"
    user.registration = null
    and: "there is an error message about missing registration what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.REGISTRATION_NOT_EXIST).arg("userId", user.id as String).build()
    when: "we confirm registration using registration code"
    userService.registration(user.id, REGISTRATION_CODE)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the expected error message is logged about missing registration"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String, _ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Confirming a registration with a wrong registration code results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "it has a new registration."
    assert user.registration.state == RegistrationState.NEW
    assert user.registration.confirmationDate == null
    and: "there is an error message about wrong registration code what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.REGISTRATION_CODE_NOT_MATCH).
            args([userId: user.id as String, registrationCode: WRONG_REGISTRATION_CODE]).build()
    when: "we confirm registration using the wrong registration code"
    userService.registration(user.id, WRONG_REGISTRATION_CODE)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the expected error message is logged about wrong registration code"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String, _ as String)
    and: "saving user is never called."
    0 * userDao.save(_ as User)
  }

  def "Confirming a registration of a not TEMPORARY user results in an error"() {
    given: "a user (in database)"
    User user = createUser(ID)
    and: "its state is not TEMPORARY"
    user.state = notTemporaryUserState
    and: "there is an error message about wrong user state what we expect to be thrown."
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.CONFIRM_REGISTRATION_WITH_WRONG_USER_STATE).
            args([correctUserState: UserState.TEMPORARY.name(), userState: user.state.name()]).build()
    when: "we confirm registration using the registration code"
    userService.registration(user.id, user.registration.registrationCode)
    then: "the user in database is queried"
    1 * userDao.findOne(user.id) >> user
    and: "the expected error message is logged about wrong user state"
    1 * logger.error(expectedErrorMessage.fullMessage())
    and: "an exception is thrown with the expected error message"
    BaseException exception = thrown(BaseException)
    checkException(exception, expectedErrorMessage)
    and: "info message is never logged about anything"
    0 * logger.info(_ as String, _ as String)
    and: "saving user is never called"
    0 * userDao.save(_ as User)
    where: "we check all not TEMPORARY user state."
    notTemporaryUserState << [UserState.NORMAL, UserState.BANNED, UserState.DORMANT, UserState.DELETED]
  }

  private User createUser(Long userId) {
    return new User().with { User newUser ->
      id = userId
      displayName = "${DISPLAY_NAME}_${userId}"
      username = "${USERNAME}_${userId}"
      email = "${EMAIL}_${userId}"
      password = ENCODED_PASSWORD
      state = UserState.TEMPORARY
      registration = new Registration().with {
        user = newUser
        state = RegistrationState.NEW
        registrationCode = "${REGISTRATION_CODE}_${userId}"
        return it
      }
      return newUser
    }
  }

  private boolean checkUserProps(UserVo userVo, User user) {
    return userVo.id == user.id &&
           userVo.displayName == user.displayName &&
           userVo.username == user.username &&
           userVo.email == user.email &&
           userVo.userState == user.state.name() &&
           userVo.registrationState == user.registration.state.name() &&
           userVo.registrationCode == user.registration.registrationCode
  }

  private boolean checkException(BaseException exception, Message expectedErrorMessage) {
    return exception.errorMessage.messageCode == expectedErrorMessage.messageCode &&
           exception.errorMessage.type == expectedErrorMessage.type &&
           exception.errorMessage.args == expectedErrorMessage.args &&
           exception.errorMessage.message == expectedErrorMessage.message
  }

}
