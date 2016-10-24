package hu.asgames.specifications.ws

import hu.asgames.specifications.ws.service.TestWebServiceRepository
import hu.asgames.ws.api.vo.UserVo

/**
 * @author AMiklo on 2016.10.20.
 */
class WebServiceSpecFixtures extends TestWebServiceRepository {

  UserVo createNewUser() {
    UserVo user = newUser()
    Long userId = userService.createUser(user)
    return userService.getUser(userId)
  }

  UserVo newUser() {
    return new UserVo().with {
      username = dummy()
      password = 'dummy'
      email = 'dummy@asgames.hu'
      return it
    }
  }

  String dummy() {
    UUID.randomUUID()
  }

}
