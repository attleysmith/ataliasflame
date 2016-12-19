package hu.asgames.ws.api.domain;

/**
 * @author AMiklo on 2016.12.05.
 */
public enum MessageType {
    ERROR("error"), WARNING("warn"), INFO("info");

    private final String code;

    MessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
