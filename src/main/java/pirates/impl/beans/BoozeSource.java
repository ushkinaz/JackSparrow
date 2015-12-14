package pirates.impl.beans;

import pirates.impl.exceptions.BoozeBiddingException;
import pirates.impl.exceptions.BoozeException;
import pirates.impl.exceptions.BoozeExhaustedException;
import pirates.impl.exceptions.BoozeTradingException;

/**
 * DTO, represents booze source, as given in CSV.
 * Immutable
 */
public class BoozeSource {
    private String name;

    /**
     * Total number of gallons available for sale
     */
    private int totalAmount;

    /**
     * Average price per gallon
     */
    private double avgPrice;

    /**
     * Will not sell if bid size is less than this number of gallons
     */
    private int minConsignment;

    /**
     * Number of gallons in a lot increments by this size.
     */
    private int stepAmount;

    public BoozeSource(String name, double avgPrice, int totalAmount, int minConsignment, int stepAmount) throws BoozeException {
        if (totalAmount < 0) {
            throw new BoozeException("Can not create empty source");
        }

        if (avgPrice <= 0) {
            throw new BoozeException("Price should be positive");
        }

        if (totalAmount < minConsignment) {
            throw new BoozeException("Total should be greater than minimal");
        }
        if (minConsignment < stepAmount) {
            throw new BoozeException("Step amount should be less or equal to minimal consignment");
        }

        if (minConsignment < stepAmount) {
            throw new BoozeException("Step amount should be less or equal to minimal consignment");
        }

        if ((totalAmount - minConsignment) % stepAmount != 0) {
            throw new BoozeException("Should be able to buy all gallons in one big package");
        }

        this.name = name;
        this.totalAmount = totalAmount;
        this.avgPrice = avgPrice;
        this.minConsignment = minConsignment;
        this.stepAmount = stepAmount;
    }

    public String getName() {
        return name;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public int getMinConsignment() {
        return minConsignment;
    }

    public int getStepAmount() {
        return stepAmount;
    }

    /**
     * Creates new source, deducting given amount of gallons from available amount
     * {@code this} instance remains untouched
     *
     * @param amount amount to deduct
     * @return new instance
     * @throws BoozeExhaustedException
     */
    public BoozeSource deduct(int amount) throws BoozeTradingException {
        if (amount < minConsignment) {
            throw new BoozeBiddingException("Consignment is too small, expected at least " + minConsignment + ". Got " + amount);
        }

        if ((amount - minConsignment) % stepAmount != 0) {
            throw new BoozeBiddingException("Consignment size is not acceptable, expected to be " + minConsignment + " + X*" + stepAmount + ". Got " + amount);
        }

        try {
            return new BoozeSource(name, avgPrice, totalAmount - amount, minConsignment, stepAmount);
        } catch (BoozeException e) {
            throw new BoozeExhaustedException("You've asked for too much");
        }
    }

    /**
     * @param gallonsToBuy buying request
     * @return maximum allowable amount of gallons we can buy from this source. 0, if buying is not allowed
     */
    public int findMaxAmountToBuy(int gallonsToBuy) {
        if (gallonsToBuy < minConsignment) {
            return minConsignment;
        }

        if (totalAmount < gallonsToBuy) {
            return totalAmount;
        }
        //else - we can buy all gallons from this source

        int incrementsToBuy = (int) (Math.ceil((double) (gallonsToBuy - minConsignment) / stepAmount) * stepAmount);

        return minConsignment + incrementsToBuy;
    }

    @Override
    public String toString() {
        return "BoozeSource{" + "name='" + name + '\'' +
                ", totalAmount=" + totalAmount +
                ", avgPrice=" + avgPrice +
                ", minBidSize=" + minConsignment +
                ", stepAmount=" + stepAmount +
                '}';
    }
}
