package hu.asgames.messages;

import hu.asgames.ws.api.domain.MessageType;

/**
 * @author AMiklo on 2016.12.16.
 */
public class PseudoMessage {

    private MessageType type;

    private String messageCode;

    private String rawMessage;

    // Constructors

    public PseudoMessage(final MessageType type, final String messageCode, final String rawMessage) {
        this.type = type;
        this.messageCode = messageCode;
        this.rawMessage = rawMessage;
    }

    // Getters and setters

    public MessageType getType() {
        return type;
    }

    public void setType(final MessageType type) {
        this.type = type;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(final String messageCode) {
        this.messageCode = messageCode;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(final String rawMessage) {
        this.rawMessage = rawMessage;
    }

}
