package pirates.impl;

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
}
