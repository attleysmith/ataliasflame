package hu.asgames.specifications.ws

import hu.asgames.specifications.ws.service.TestWebServiceRepository
import hu.asgames.ws.api.vo.UserVo

/**
 * @author AMiklo on 2016.10.20.
 */
class WebServiceSpecFixtures extends TestWebServiceRepository {

  UserVo createUser() {
    UserVo user = new UserVo().with {
      username = UUID.randomUUID()
      password = 'dummy'
      email = 'dummy@asgames.hu'
      return it
    }
    Long userId = userService.createUser(user)
    return userService.getUser(userId)
  }

}
