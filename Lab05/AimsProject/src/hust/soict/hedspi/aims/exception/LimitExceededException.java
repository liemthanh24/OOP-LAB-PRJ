package hust.soict.hedspi.aims.exception;

public class LimitExceededException extends Exception {
    public LimitExceededException() {
        super();
    }

    public LimitExceededException(String message) {
        super(message);
    }

    public LimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitExceededException(Throwable cause) {
        super(cause);
    }
}
