package hu.asgames.ws.api.domain;

import java.time.LocalDateTime;

/**
 * @author AMiklo on 2016.11.02.
 */
public class BaseRequest {

    private LocalDateTime requestTime;
    private String clientCode;

    // Getters and setters

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(final LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(final String clientCode) {
        this.clientCode = clientCode;
    }
}
