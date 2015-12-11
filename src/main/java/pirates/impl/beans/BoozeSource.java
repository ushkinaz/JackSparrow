package pirates.impl.beans;

/**
 * DTO, represents booze sourse, as given in CSV
 */
public class BoozeSource {
    private String name;
    private int size;
    private double avgPrice;
    private int minBidSize;
    private int stepSize;

    public BoozeSource(String name, int size, double avgPrice, int minBidSize, int stepSize) {
        this.name = name;
        this.size = size;
        this.avgPrice = avgPrice;
        this.minBidSize = minBidSize;
        this.stepSize = stepSize;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public int getMinBidSize() {
        return minBidSize;
    }

    public int getStepSize() {
        return stepSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BoozeSource");
        sb.append("{name='").append(name).append('\'');
        sb.append(", size=").append(size);
        sb.append(", avgPrice=").append(avgPrice);
        sb.append(", minBidSize=").append(minBidSize);
        sb.append(", stepSize=").append(stepSize);
        sb.append('}');
        return sb.toString();
    }
}
