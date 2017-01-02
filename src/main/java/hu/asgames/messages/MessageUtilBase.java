package hu.asgames.messages;

import hu.asgames.ws.api.domain.Message;
import hu.asgames.ws.api.domain.MessageType;

import java.util.HashMap;

/**
 * @author AMiklo on 2016.12.08.
 */
public abstract class MessageUtilBase {

    public static Message generalException(String errorMessage) {
        return new Message(MessageType.ERROR, "general.exception", errorMessage, new HashMap<>());
    }

    protected static PseudoMessage error(String messageCode, String rawMessage) {
        return new PseudoMessage(MessageType.ERROR, messageCode, rawMessage);
    }

    protected static PseudoMessage info(String messageCode, String rawMessage) {
        return new PseudoMessage(MessageType.INFO, messageCode, rawMessage);
    }

    protected static PseudoMessage warn(String messageCode, String rawMessage) {
        return new PseudoMessage(MessageType.WARNING, messageCode, rawMessage);
    }
}
