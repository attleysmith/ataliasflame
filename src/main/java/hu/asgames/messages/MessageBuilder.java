package hu.asgames.messages;

import hu.asgames.ws.api.domain.Message;
import hu.asgames.ws.api.domain.MessageType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author AMiklo on 2016.12.16.
 */
public class MessageBuilder {

    private final MessageType type;
    private final String messageCode;
    private final String rawMessage;
    private final Map<String, String> args = new HashMap<>();

    public MessageBuilder(PseudoMessage pseudoMessage) {
        this.type = pseudoMessage.getType();
        this.messageCode = pseudoMessage.getMessageCode();
        this.rawMessage = pseudoMessage.getRawMessage();
    }

    public MessageBuilder arg(String argumentCode, String argument) {
        args.put(argumentCode, argument);
        return this;
    }

    public MessageBuilder args(Map<String, String> arguments) {
        args.putAll(arguments);
        return this;
    }

    public Message build() {
        String resolvedMessage = getResolvedMessage();
        return new Message(type, messageCode, resolvedMessage, args);
    }

    private String getResolvedMessage() {
        String message = rawMessage;
        for (Map.Entry<String, String> arg : args.entrySet()) {
            message = message.replaceAll(Pattern.quote("${" + arg.getKey() + "}"), arg.getValue());
        }
        return message;
    }
}
