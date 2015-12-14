package pirates.impl;

import pirates.impl.beans.BoozeSource;
import pirates.impl.beans.PartialPurchase;
import pirates.impl.exceptions.BoozeExhaustedException;

import java.util.Collection;
import java.util.Set;

public interface BuyingStrategy {
    Collection<PartialPurchase> findBids(Set<BoozeSource> sources, int gallons) throws BoozeExhaustedException;
}
