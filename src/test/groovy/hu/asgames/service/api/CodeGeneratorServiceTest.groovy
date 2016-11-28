package hu.asgames.service.api

import hu.asgames.service.impl.CodeGeneratorServiceImpl
import spock.lang.Specification

/**
 * @author AMiklo on 2016.11.14.
 */
class CodeGeneratorServiceTest extends Specification {

  private final CodeGeneratorService codeGeneratorService = new CodeGeneratorServiceImpl()

  def "Generating registration code results in alphanumeric value"() {
    when: "we generate a registration code"
    String registrationCode = codeGeneratorService.generateRegistrationCode()
    then: "the result is a 32-length alphanumeric string with upper case characters."
    registrationCode.matches("[0-9A-Z]{32}")
  }
}
