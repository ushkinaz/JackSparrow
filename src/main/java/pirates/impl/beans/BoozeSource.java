package pirates.impl.beans;

import pirates.impl.BoozeBiddingException;
import pirates.impl.BoozeExhaustedException;
import pirates.impl.BoozeTradingException;

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

    public BoozeSource(String name, int totalAmount, double avgPrice, int minConsignment, int stepAmount) {
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

        if (totalAmount - amount < 0) {
            throw new BoozeExhaustedException("You've asked for too much");
        }

        return new BoozeSource(name, totalAmount - amount, avgPrice, minConsignment, stepAmount);
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
