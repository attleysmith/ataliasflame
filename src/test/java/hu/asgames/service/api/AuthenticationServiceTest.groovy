package hu.asgames.service.api

import hu.asgames.report.Report
import hu.asgames.service.impl.AuthenticationServiceImpl
import spock.lang.Specification

/**
 * @author AMiklo on 2016.10.19.
 */
@Report
class AuthenticationServiceTest extends Specification {

  private AuthenticationService authenticationService = new AuthenticationServiceImpl();

  def "Password encoding and checking are synchronized"() {
    when: "there is a password to use"
    String correctPassword = 'verysecretpassword'
    and: "there is a misspelled one"
    String wrongPassword = 'verzsecretpassword'
    and: "we encode the correct password to store"
    String encodedPassword = authenticationService.encodePassword(correctPassword)
    then: "checking the correct one succeed"
    authenticationService.checkPassword(correctPassword, encodedPassword)
    and: "checking the wrong one fail"
    !authenticationService.checkPassword(wrongPassword, encodedPassword)
  }
}
