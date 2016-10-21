package hu.asgames.specifications.ws.service

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import hu.asgames.ws.api.vo.UserVo

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
    HttpResponseDecorator response = restClient.get(path: ROOT_PATH)
    assert response.status == 200
    return response.data
  }

  Long createUser(UserVo user) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/create", body: user)
    assert response.status == 200
    return response.data
  }

  UserVo getUser(Long id) {
    HttpResponseDecorator response = restClient.get(path: ROOT_PATH + "/" + id)
    assert response.status == 200
    return response.data
  }

  void modifyUser(UserVo user) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/" + user.id + "/modify", body: user)
    assert response.status == 200
  }

  void deleteUser(Long id) {
    HttpResponseDecorator response = restClient.post(path: ROOT_PATH + "/" + id + "/delete")
    assert response.status == 200
  }
}
