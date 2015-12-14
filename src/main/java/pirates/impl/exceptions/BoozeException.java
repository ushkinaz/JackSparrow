package pirates.impl.exceptions;

/**
 * Generic issues
 */
public class BoozeException extends Exception {
    public BoozeException(String message) {
        super(message);
    }

    public BoozeException(Throwable cause) {
        super(cause);
    }
}
