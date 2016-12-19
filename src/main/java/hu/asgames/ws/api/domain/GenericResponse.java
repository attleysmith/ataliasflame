package hu.asgames.ws.api.domain;

/**
 * @author AMiklo on 2016.11.02.
 */
public class GenericResponse<T> extends BaseResponse {

    private T responseBody;

    // Getters and setters

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(final T responseBody) {
        this.responseBody = responseBody;
    }
}
