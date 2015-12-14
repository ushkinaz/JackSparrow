package pirates.impl;

import pirates.Purchases;
import pirates.impl.beans.BoozeSource;

import java.util.Set;

public interface BuyingStrategy {
    Purchases findBids(int gallons, Set<BoozeSource> sources);
}

/*
* Bad case to consider
* need 100
* 50*1 @ 1
* have  1*51 @ 2
* have 50*1 @ 300
*
* winning strategy is
* 49*1 @ 1
* 1*51 @ 2
*
* */
