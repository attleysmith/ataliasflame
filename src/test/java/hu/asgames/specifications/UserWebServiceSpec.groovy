package hu.asgames.specifications

import hu.asgames.report.Report
import hu.asgames.specifications.ws.WebServiceSpecFixtures
import hu.asgames.ws.api.vo.UserVo

/**
 * @author AMiklo on 2016.10.20.
 */
@Report
class UserWebServiceSpec extends WebServiceSpecFixtures {

  def "User list is available"() {
    when: "we get the list of users"
    List<UserVo> userList = userService.getUserList()
    then: "at start there is only one user"
    userList.size() == 1
    and: "the user name is admin."
    userList[0].username == 'admin'
  }

  def "User creation succeed"() {
    when: "we take a new user"
    UserVo user = new UserVo().with {
      username = 'user'
      password = 'pw'
      email = 'user@asgames.hu'
      return it
    }
    and: "we save it"
    Long userId = userService.createUser(user)
    then: "we can get the user by id"
    UserVo savedUser = userService.getUser(userId)
    and: "the user has a TEMPORARY status"
    savedUser.userState == 'TEMPORARY'
    and: "it has a valid registration"
    savedUser.registrationCode == 'DUMMY'
    savedUser.registrationState == 'NEW'
    and: "given data are stored"
    savedUser.username == 'user'
    savedUser.email == 'user@asgames.hu'
    and: "the returned password is empty."
    savedUser.password == null
  }

  def "User modification succeed"() {
    when: "we have a user"
    UserVo user = createUser()
    and: "make some changes on her user data"
    user.username = 'newusername'
    user.email = 'newemail@asgames.hu'
    and: "save modifications"
    userService.modifyUser(user)
    then: "the changes are executed."
    UserVo modifiedUser = userService.getUser(user.id)
    modifiedUser.username == 'newusername'
    modifiedUser.email == 'newemail@asgames.hu'
  }

  def "User deletion succeed"() {
    when: "we have a user"
    UserVo user = createUser()
    and: "delete user"
    userService.deleteUser(user.id)
    then: "the deletion is performed."
    UserVo deletedUser = userService.getUser(user.id)
    deletedUser.userState == 'DELETED'
  }
}
