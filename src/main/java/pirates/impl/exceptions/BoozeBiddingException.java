package pirates.impl.exceptions;

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
