package pirates.impl.beans;

import java.util.Collection;
import java.util.List;

/**
 * Market snapshot after some purchases are done
 */
public class PartialMarketState {
    private List<BoozeSource> sources;
    private Collection<PartialPurchase> purchases;
    /**
     * Initial need for gallons
     */
    private int initialRequest;
    /**
     * Remaining gallons to buy
     */
    private int gallonsToBuy;

    public PartialMarketState(List<BoozeSource> sources, Collection<PartialPurchase> purchases, int initialRequest, int gallonsToBuy) {
        this.sources = sources;
        this.purchases = purchases;
        this.initialRequest = initialRequest;
        this.gallonsToBuy = gallonsToBuy;
    }

    public List<BoozeSource> getSources() {
        return sources;
    }

    public Collection<PartialPurchase> getPurchases() {
        return purchases;
    }

    public int getInitialRequest() {
        return initialRequest;
    }

    public int getGallonsToBuy() {
        return gallonsToBuy;
    }
}
