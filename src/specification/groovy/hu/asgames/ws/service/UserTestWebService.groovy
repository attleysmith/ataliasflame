package hu.asgames.ws.service

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import hu.asgames.ws.api.domain.BaseRequest
import hu.asgames.ws.api.domain.GenericRequest
import hu.asgames.ws.api.domain.ResponseStatus
import hu.asgames.ws.api.domain.user.*

import java.time.LocalDateTime

/**
 * @author AMiklo on 2016.10.20.
 */
class UserTestWebService {

  private static final String ROOT_PATH = "/user"

  private final RESTClient restClient
  private final String clientCode

  UserTestWebService(RESTClient restClient, String clientCode) {
    this.restClient = restClient
    this.clientCode = clientCode
  }

  List<UserVo> getUserList() {
    post("/list", new BaseRequest())
  }

  Long createUser(CreateUserRequest requestBody) {
    post("/create", new GenericRequest<CreateUserRequest>(), requestBody)
  }

  UserVo getUser(Long id) {
    post("/get/$id", new BaseRequest())
  }

  void modifyUser(Long id, ModifyUserRequest requestBody) {
    post("/modify/$id", new GenericRequest<ModifyUserRequest>(), requestBody)
  }

  void deleteUser(Long id) {
    post("/delete/$id", new BaseRequest())
  }

  void changePassword(Long id, ChangePasswordRequest requestBody) {
    post("/password/$id", new GenericRequest<ChangePasswordRequest>(), requestBody)
  }

  Long login(LoginRequest requestBody) {
    post("/login", new GenericRequest<LoginRequest>(), requestBody)
  }

  UserVo registration(String registrationCode) {
    post("/registration/$registrationCode", new BaseRequest())
  }

  private <Q extends BaseRequest, B, R> R post(String path, Q request, B requestBody = null) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + path,
                                                     body: createRequest(request, requestBody))
    assert response.status == 200
    assert response.data.responseStatus == ResponseStatus.OK.name()
    return response.data.responseBody
  }

  private <R extends BaseRequest, B> R createRequest(R request, B requestBody = null) {
    request.setRequestTime(LocalDateTime.now())
    request.setClientCode(clientCode)

    if (request instanceof GenericRequest) {
      request.setRequestBody(requestBody)
    }

    return request
  }
}
