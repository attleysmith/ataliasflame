package hu.asgames.messages

import hu.asgames.ws.api.domain.Message
import hu.asgames.ws.api.domain.MessageType
import spock.lang.Specification

import static hu.asgames.messages.MessageBuilderTest.DummyMessageUtil.ERROR_WITHOUT_ARGS
import static hu.asgames.messages.MessageBuilderTest.DummyMessageUtil.ERROR_WITH_ARGS

/**
 * @author AMiklo on 2016.12.08.
 */
class MessageBuilderTest extends Specification {

  private static final String EXPECTED_MESSAGE = 'To be, or not to be, that is the question'
  private static final String PARAM_1 = 'be'
  private static final String PARAM_2 = 'question'
  private static final String RAW_ERROR_MESSAGE_WITH_ARGS = 'To ${key1}, or not to ${key1}, that is the ${key2}'
  private static final String RAW_ERROR_MESSAGE_WITHOUT_ARGS = EXPECTED_MESSAGE
  private static final String ERROR_WITH_ARGS_CODE = 'message_with_args_code'
  private static final String ERROR_WITHOUT_ARGS_CODE = 'message_without_args_code'

  def "MessageBuilder can set args as a map and provides a fully built message structure"() {
    when: "we build a message from a pseudo message with adding an argument map"
    Message message = new MessageBuilder(ERROR_WITH_ARGS).args([key1: PARAM_1, key2: PARAM_2]).build()
    then: "the result owns the correct type and code inherited from the pseudo message"
    message.type == MessageType.ERROR
    message.messageCode == ERROR_WITH_ARGS_CODE
    and: "holds the added arguments"
    message.args['key1'] == PARAM_1
    message.args['key2'] == PARAM_2
    and: "we get the expected message string."
    message.message == EXPECTED_MESSAGE
    message.fullMessage == "error:$ERROR_WITH_ARGS_CODE - $EXPECTED_MESSAGE"
  }

  def "MessageBuilder can set args one-by-one and provides a fully built message structure"() {
    when: "we build a message from a pseudo message with adding arguments one-by-one"
    Message message = new MessageBuilder(ERROR_WITH_ARGS).arg('key1', PARAM_1).arg('key2', PARAM_2).build()
    then: "the result owns the correct type and code inherited from the pseudo message"
    message.type == MessageType.ERROR
    message.messageCode == ERROR_WITH_ARGS_CODE
    and: "holds the added arguments"
    message.args['key1'] == PARAM_1
    message.args['key2'] == PARAM_2
    and: "we get the expected message string."
    message.message == EXPECTED_MESSAGE
    message.fullMessage == "error:$ERROR_WITH_ARGS_CODE - $EXPECTED_MESSAGE"
  }

  def "MessageBuilder can provide a fully built message structure without a need of args"() {
    when: "we build a message from a pseudo message without adding any arguments"
    Message message = new MessageBuilder(ERROR_WITHOUT_ARGS).build()
    then: "the result owns the correct type and code inherited from the pseudo message"
    message.type == MessageType.ERROR
    message.messageCode == ERROR_WITHOUT_ARGS_CODE
    and: "the argument map is empty"
    message.args.isEmpty()
    and: "we get the expected message string."
    message.message == EXPECTED_MESSAGE
    message.fullMessage == "error:$ERROR_WITHOUT_ARGS_CODE - $EXPECTED_MESSAGE"
  }

  private final class DummyMessageUtil extends MessageUtilBase {

    private DummyMessageUtil() {}

    public static final PseudoMessage ERROR_WITH_ARGS = error(ERROR_WITH_ARGS_CODE, RAW_ERROR_MESSAGE_WITH_ARGS)

    public static final PseudoMessage ERROR_WITHOUT_ARGS = error(ERROR_WITHOUT_ARGS_CODE, RAW_ERROR_MESSAGE_WITHOUT_ARGS)

  }
}
