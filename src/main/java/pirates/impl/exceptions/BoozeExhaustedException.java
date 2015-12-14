package pirates.impl.exceptions;

/**
 * Booze has come to end
 */
public class BoozeExhaustedException extends BoozeTradingException {
    public BoozeExhaustedException(String message) {
        super(message);
    }

    public BoozeExhaustedException(Throwable cause) {
        super(cause);
    }

    public BoozeExhaustedException(String message, Throwable cause) {
        super(message, cause);
    }
}
