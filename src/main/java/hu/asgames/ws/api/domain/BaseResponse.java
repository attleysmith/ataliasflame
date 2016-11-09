package hu.asgames.ws.api.domain;

import java.time.LocalDateTime;

/**
 * @author AMiklo on 2016.11.02.
 */
public class BaseResponse {

    private LocalDateTime responseTime;

    private ResponseStatus responseStatus;

    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
