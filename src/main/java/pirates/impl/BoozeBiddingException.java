package pirates.impl;

/**
 * Indicates issues with bidding
 */
public class BoozeBiddingException extends BoozeTradingException {
    public BoozeBiddingException(String message) {
        super(message);
    }

    public BoozeBiddingException(Throwable cause) {
        super(cause);
    }
}
