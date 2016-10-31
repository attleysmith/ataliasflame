package hu.asgames.specifications.ws.service

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import hu.asgames.ws.api.vo.user.*

/**
 * @author AMiklo on 2016.10.20.
 */
class UserTestWebService {

  private static final String ROOT_PATH = "/user"

  private final RESTClient restClient;

  UserTestWebService(RESTClient restClient) {
    this.restClient = restClient
  }

  List<UserVo> getUserList() {
    HttpResponseDecorator response = restClient.get(path: ROOT_PATH + "/get")
    assert response.status == 200
    return response.data
  }

  Long createUser(CreateUserRequest request) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/create", body: request)
    assert response.status == 200
    return response.data
  }

  UserVo getUser(Long id) {
    HttpResponseDecorator response = restClient.get(path: ROOT_PATH + "/get/" + id)
    assert response.status == 200
    return response.data
  }

  void modifyUser(Long id, ModifyUserRequest request) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/modify/" + id, body: request)
    assert response.status == 200
  }

  void deleteUser(Long id) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/delete/" + id)
    assert response.status == 200
  }

  void changePassword(Long id, ChangePasswordRequest request) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/password/" + id, body: request)
    assert response.status == 200
  }

  Long login(LoginRequest request) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/login", body: request)
    assert response.status == 200
    return response.data
  }
}
