package hu.asgames.ws.api.domain;

/**
 * @author AMiklo on 2016.11.02.
 */
public class GenericRequest<T> extends BaseRequest {

    private T requestBody;

    public T getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(final T requestBody) {
        this.requestBody = requestBody;
    }
}
