package pirates.impl;

/**
 * Booze has come to end
 */
public class BoozeTradingException extends Exception {
    public BoozeTradingException(String message) {
        super(message);
    }

    public BoozeTradingException(Throwable cause) {
        super(cause);
    }

    public BoozeTradingException(String message, Throwable cause) {
        super(message, cause);
    }
}
