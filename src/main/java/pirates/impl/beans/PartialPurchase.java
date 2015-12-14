package pirates.impl.beans;

public class PartialPurchase {
    private BoozeSource source;
    private int gallonsBought;

    public PartialPurchase(BoozeSource source, int gallonsBought) {
        this.source = source;
        this.gallonsBought = gallonsBought;
    }

    public BoozeSource getSource() {
        return source;
    }

    public int getGallonsBought() {
        return gallonsBought;
    }

    @Override
    public String toString() {
        return "PartialPurchase{" + "source=" + source +
                ", gallonsBought=" + gallonsBought +
                '}';
    }
}
