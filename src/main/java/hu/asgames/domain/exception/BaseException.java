package hu.asgames.domain.exception;

import hu.asgames.ws.api.domain.Message;

/**
 * @author AMiklo on 2016.12.05.
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 4417395822932619382L;

    private final Message errorMessage;

    public BaseException(final Message errorMessage) {
        super(errorMessage.getFullMessage());
        this.errorMessage = errorMessage;
    }

    public BaseException(final Message errorMessage, final Throwable cause) {
        super(errorMessage.getFullMessage(), cause);
        this.errorMessage = errorMessage;
    }

    public Message getErrorMessage() {
        return errorMessage;
    }
}
