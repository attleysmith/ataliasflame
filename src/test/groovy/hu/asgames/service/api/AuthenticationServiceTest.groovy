package hu.asgames.service.api

import hu.asgames.report.Report
import hu.asgames.service.impl.AuthenticationServiceImpl
import spock.lang.Specification

/**
 * @author AMiklo on 2016.10.19.
 */
@Report
class AuthenticationServiceTest extends Specification {

  private final AuthenticationService authenticationService = new AuthenticationServiceImpl()

  def "Password encoding and checking are synchronized"() {
    given: "a password to use"
    String correctPassword = 'verysecretpassword'
    and: "there is also a misspelled one."
    String wrongPassword = 'verzsecretpassword'
    when: "we encode the correct password to store"
    String encodedPassword = authenticationService.encodePassword(correctPassword)
    then: "checking the correct one succeeds"
    authenticationService.checkPassword(correctPassword, encodedPassword)
    and: "checking the wrong one fails."
    !authenticationService.checkPassword(wrongPassword, encodedPassword)
  }
}
