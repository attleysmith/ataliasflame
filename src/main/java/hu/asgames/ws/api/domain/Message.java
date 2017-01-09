package hu.asgames.ws.api.domain;

import java.util.Map;

/**
 * @author AMiklo on 2016.12.05.
 */
public class Message {

    private MessageType type;
    private String messageCode;
    private String message;
    private Map<String, String> args;

    // Constructors

    public Message(final MessageType type, final String messageCode, final String message, final Map<String, String> args) {
        this.type = type;
        this.messageCode = messageCode;
        this.message = message;
        this.args = args;
    }

    // Object methods

    public String fullMessage() {
        return type.getCode() + ':' + messageCode + " - " + message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(final Map<String, String> args) {
        this.args = args;
    }
}
