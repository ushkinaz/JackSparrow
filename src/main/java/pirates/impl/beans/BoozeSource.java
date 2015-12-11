package pirates.impl.beans;

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
    private int minBidSize;

    /**
     * Number of gallons in a lot increments by this size.
     */
    private int stepAmount;

    public BoozeSource(String name, int totalAmount, double avgPrice, int minBidSize, int stepAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.avgPrice = avgPrice;
        this.minBidSize = minBidSize;
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

    public int getMinBidSize() {
        return minBidSize;
    }

    public int getStepAmount() {
        return stepAmount;
    }

    //  Creates new source, deducting given amount of gallons from available amount;
    public BoozeSource deduct(int amount) {
        //TODO: Implement pirates.impl.JackSparrowHelperImpl#helpJackSparrow
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        return "BoozeSource{" + "name='" + name + '\'' +
                ", totalAmount=" + totalAmount +
                ", avgPrice=" + avgPrice +
                ", minBidSize=" + minBidSize +
                ", stepAmount=" + stepAmount +
                '}';
    }
}
