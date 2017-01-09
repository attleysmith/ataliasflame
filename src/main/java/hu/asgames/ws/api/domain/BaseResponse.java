package hu.asgames.ws.api.domain;

import java.time.LocalDateTime;

/**
 * @author AMiklo on 2016.11.02.
 */
public class BaseResponse {

    private LocalDateTime responseTime;
    private ResponseStatus responseStatus;
    private Message responseMessage;

    // Getters and setters

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(final LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(final ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Message getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(final Message responseMessage) {
        this.responseMessage = responseMessage;
    }
}
