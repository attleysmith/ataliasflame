package hu.asgames.messages

import hu.asgames.ws.api.domain.MessageType
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author AMiklo on 2016.12.16.
 */
class MessageUtilBaseTest extends Specification {

  private static final String MESSAGE_CODE = 'dummy.error.message'
  private static final String RAW_MESSAGE = 'Dummy raw message.'

  @Unroll
  def "Calling method '#calledMethod' provides a pseudo message with #expectedType type"() {
    given: "a message util which extends MessageUtilBase."
    assert MessageUtilBase.isAssignableFrom(DummyMessageUtil.class)
    when: "we call a method on this util passing a message code and a raw message"
    PseudoMessage pseudoMessage = DummyMessageUtil."${calledMethod}"(MESSAGE_CODE, RAW_MESSAGE)
    then: "we get a pseudo message with a proper type"
    pseudoMessage.type == expectedType
    and: "the passed data is set on it."
    pseudoMessage.messageCode == MESSAGE_CODE
    pseudoMessage.rawMessage == RAW_MESSAGE
    where:
    calledMethod | expectedType
    "error"      | MessageType.ERROR
    "info"       | MessageType.INFO
    "warn"       | MessageType.WARNING
  }

  private final class DummyMessageUtil extends MessageUtilBase {

    private DummyMessageUtil() {}

  }
}
