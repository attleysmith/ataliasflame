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
    then: "at start there is at least one user"
    userList.size() >= 1
    and: "there is a user, named admin."
    userList.find({ it.username == 'admin' }) != null
  }

  def "User creation succeed"() {
    when: "we take a new user"
    UserVo newUser = newUser()
    and: "we save it"
    Long userId = userService.createUser(newUser)
    then: "we can get the user by id"
    UserVo savedUser = userService.getUser(userId)
    and: "the user has a TEMPORARY status"
    savedUser.userState == 'TEMPORARY'
    and: "it has a valid registration"
    savedUser.registrationCode == 'DUMMY'
    savedUser.registrationState == 'NEW'
    and: "given data are stored"
    savedUser.username == newUser.username
    savedUser.email == newUser.email
    and: "the returned password is empty."
    savedUser.password == null
  }

  def "User modification succeed"() {
    when: "we have a user"
    UserVo user = createNewUser()
    and: "make some changes on her user data"
    user.username = dummy()
    user.email = 'newemail@asgames.hu'
    and: "save modifications"
    userService.modifyUser(user)
    then: "the changes are executed."
    UserVo modifiedUser = userService.getUser(user.id)
    modifiedUser.username == user.username
    modifiedUser.email == user.email
  }

  def "User deletion succeed"() {
    when: "we have a user"
    UserVo user = createNewUser()
    and: "delete user"
    userService.deleteUser(user.id)
    then: "the deletion is performed."
    UserVo deletedUser = userService.getUser(user.id)
    deletedUser.userState == 'DELETED'
  }
}
