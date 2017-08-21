package hu.asgames.ws.api

import hu.asgames.domain.enums.RegistrationState
import hu.asgames.domain.enums.UserState
import hu.asgames.domain.exceptions.BaseException
import hu.asgames.messages.MessageBuilder
import hu.asgames.messages.MessageUtil
import hu.asgames.service.api.UserService
import hu.asgames.ws.api.domain.*
import hu.asgames.ws.api.domain.user.*
import hu.asgames.ws.impl.UserWebServiceImpl
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * @author AMiklo on 2016.12.21.
 */
class UserWebServiceTest extends Specification {

  private static final String CLIENT_CODE = "UNIT_TEST"
  private static final Long ID = 123456L
  private static final String DISPLAY_NAME = "DISPLAY_NAME"
  private static final String USERNAME = "USERNAME"
  private static final String EMAIL = "EMAIL"
  private static final String PASSWORD = "PASSWORD"
  private static final String REGISTRATION_CODE = "REGISTRATION_CODE"
  private static final String NEW_PASSWORD = "NEW_PASSWORD"

  private final UserWebService userWebService = new UserWebServiceImpl()
  private UserService userService = Mock()

  void setup() {
    userWebService.userService = userService
  }

  def "Getting user list as webservice response returns with the service provided result"() {
    given: "a list containing various amount of users"
    List<UserVo> userList = createUserList(userCount)
    and: "a base webservice request for getting user list."
    BaseRequest request = createRequest()
    when: "we pass request to get the user list"
    GenericResponse<List<UserVo>> response = userWebService.getUserList(request)
    then: "the proper service method is called"
    1 * userService.getUserList() >> userList
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response"
    response.responseMessage == null
    and: "the response body contains as many user as expected"
    response.responseBody.size() == userCount
    and: "the users are the service provided ones"
    for (int i = 0; i < userCount; i++) {
      assert response.responseBody[i] == userList[i]
    }
    where: "we check some relevant amount of users."
    userCount << [0, 1, 2, 3]
  }

  def "User creation calls proper service method and passes the returned user identifier"() {
    given: "a user creation request"
    CreateUserRequest requestBody = new CreateUserRequest().with {
      displayName = DISPLAY_NAME
      username = USERNAME
      email = EMAIL
      password = PASSWORD
      return it
    }
    and: "the request is wrapped in a generic webservice request."
    GenericRequest<CreateUserRequest> request = createRequest(requestBody)
    when: "we pass request to create the user"
    GenericResponse<Long> response = userWebService.createUser(request)
    then: "the proper service method is called"
    1 * userService.createUser(requestBody) >> ID
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response"
    response.responseMessage == null
    and: "the response body contains the user identifier returned from the service call."
    response.responseBody == ID
  }

  def "Getting user as webservice response returns with the service provided result"() {
    given: "a user"
    UserVo user = createUser(ID)
    and: "a base webservice request for getting user."
    BaseRequest request = createRequest()
    when: "we pass request to get the user"
    GenericResponse<UserVo> response = userWebService.getUser(user.id, request)
    then: "the proper service method is called"
    1 * userService.getUser(user.id) >> user
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response"
    response.responseMessage == null
    and: "the response body contains the user returned from the service call."
    response.responseBody == user
  }

  def "User modification calls proper service method"() {
    given: "a user modification request"
    ModifyUserRequest requestBody = new ModifyUserRequest().with {
      displayName = DISPLAY_NAME
      username = USERNAME
      email = EMAIL
      return it
    }
    and: "the request is wrapped in a generic webservice request."
    GenericRequest<ModifyUserRequest> request = createRequest(requestBody)
    when: "we pass request to create the user"
    BaseResponse response = userWebService.modifyUser(ID, request)
    then: "the proper service method is called"
    1 * userService.modifyUser(ID, requestBody)
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response."
    response.responseMessage == null
  }

  def "User deletion calls proper service method"() {
    given: "a base webservice request for user deletion."
    BaseRequest request = createRequest()
    when: "we pass request to delete the user"
    BaseResponse response = userWebService.deleteUser(ID, request)
    then: "the proper service method is called"
    1 * userService.deleteUser(ID)
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response."
    response.responseMessage == null
  }

  def "Changing password calls proper service method"() {
    given: "a password changing request"
    ChangePasswordRequest requestBody = new ChangePasswordRequest().with {
      oldPassword = PASSWORD
      newPassword = NEW_PASSWORD
      return it
    }
    and: "the request is wrapped in a generic webservice request."
    GenericRequest<ChangePasswordRequest> request = createRequest(requestBody)
    when: "we pass request to change the password"
    BaseResponse response = userWebService.changePassword(ID, request)
    then: "the proper service method is called"
    1 * userService.changePassword(ID, requestBody)
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response."
    response.responseMessage == null
  }

  def "Login attempt calls proper service method and passes the returned user identifier"() {
    given: "a login request"
    LoginRequest requestBody = new LoginRequest().with {
      username = USERNAME
      password = PASSWORD
      return it
    }
    and: "the request is wrapped in a generic webservice request."
    GenericRequest<LoginRequest> request = createRequest(requestBody)
    when: "we pass request to login"
    GenericResponse<Long> response = userWebService.login(request)
    then: "the proper service method is called"
    1 * userService.login(requestBody) >> ID
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response"
    response.responseMessage == null
    and: "the response body contains the user identifier returned from the service call."
    response.responseBody == ID
  }

  def "Confirming registration calls proper service method"() {
    given: "a base webservice request for confirming a registration."
    BaseRequest request = createRequest()
    when: "we pass request to confirm registration"
    BaseResponse response = userWebService.registration(ID, REGISTRATION_CODE, request)
    then: "the proper service method is called"
    1 * userService.confirmRegistration(ID, REGISTRATION_CODE)
    and: "the response has an OK status with a valid timestamp"
    response.responseStatus == ResponseStatus.OK
    response.responseTime != null
    and: "no message is attached to the response."
    response.responseMessage == null
  }

  def "Custom error message properly wrapped into exception returns with webservice response"() {
    given: "an error expected to be thrown"
    Message expectedErrorMessage = new MessageBuilder(MessageUtil.USER_NOT_EXIST).arg("userId", ID as String).build()
    Exception exception = new BaseException(expectedErrorMessage)
    and: "a base webservice request for user deletion."
    BaseRequest request = createRequest()
    when: "we pass request to delete the user"
    BaseResponse response = userWebService.deleteUser(ID, request)
    then: "the proper service method is called (returning with the expected error)"
    1 * userService.deleteUser(ID) >> { throw exception }
    and: "the response has an ERROR status with a valid timestamp"
    response.responseStatus == ResponseStatus.ERROR
    response.responseTime != null
    and: "the expected error message is passed into the response."
    response.responseMessage == expectedErrorMessage
  }

  def "System default exception returns with webservice response as general exception"() {
    given: "an error expected to be thrown"
    Exception exception = new NullPointerException("Some error...")
    and: "a base webservice request for user deletion."
    BaseRequest request = createRequest()
    when: "we pass request to delete the user"
    BaseResponse response = userWebService.deleteUser(ID, request)
    then: "the proper service method is called (returning with the expected error)"
    1 * userService.deleteUser(ID) >> { throw exception }
    and: "the response has an ERROR status with a valid timestamp"
    response.responseStatus == ResponseStatus.ERROR
    response.responseTime != null
    and: "the expected error message is passed into the response."
    response.responseMessage.type == MessageType.ERROR
    response.responseMessage.messageCode == "general.exception"
    response.responseMessage.message == "NullPointerException - Some error..."
    response.responseMessage.args.isEmpty()
  }

  def "Unexpected exception returns with webservice response as general exception"() {
    given: "a base webservice request for user deletion."
    BaseRequest request = createRequest()
    when: "we pass bad request to delete the user"
    BaseResponse response = userWebService.deleteUser(null, request)
    then: "the desired service method is never called"
    0 * userService.deleteUser(_ as Long)
    and: "the response has an ERROR status with a valid timestamp"
    response.responseStatus == ResponseStatus.ERROR
    response.responseTime != null
    and: "the error message is passed into the response."
    response.responseMessage.type == MessageType.ERROR
    response.responseMessage.messageCode == "general.exception"
    response.responseMessage.message == "NullPointerException - null"
    response.responseMessage.args.isEmpty()
  }

  private BaseRequest createRequest() {
    return withRequestData(new BaseRequest())
  }

  private <B> GenericRequest<B> createRequest(B body) {
    return withRequestData(new GenericRequest<B>().with {
      requestBody = body
      return it
    })
  }

  private <R extends BaseRequest> R withRequestData(R request) {
    request.requestTime = LocalDateTime.now()
    request.clientCode = CLIENT_CODE
    return request
  }

  private UserVo createUser(Long userId) {
    return new UserVo().with {
      id = userId
      displayName = "${DISPLAY_NAME}_${userId}"
      username = "${USERNAME}_${userId}"
      email = "${EMAIL}_${userId}"
      userState = UserState.TEMPORARY.name()
      registrationState = RegistrationState.NEW.name()
      registrationCode = "${REGISTRATION_CODE}_${userId}"
      return it
    }
  }

  private List<UserVo> createUserList(int count) {
    List<UserVo> userList = []
    for (int i = 0; i < count; i++) {
      userList << createUser(i)
    }
    return userList
  }
}
