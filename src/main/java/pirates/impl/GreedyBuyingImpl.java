package pirates.impl;

import org.slf4j.Logger;
import pirates.impl.beans.BoozeSource;
import pirates.impl.beans.PartialMarketState;
import pirates.impl.beans.PartialPurchase;
import pirates.impl.exceptions.BoozeExhaustedException;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <bq>Follows the problem solving heuristic of making the locally optimal
 * choice at each stage with the hope of finding a global optimum.</bq>
 * <p>
 * This strategy is not helpful when we need some sort of backtracking.
 * <p>
 * Bad case to consider:
 * need 100
 * selling 50*1 gallons @ $1
 * selling 1*51 gallons @ $2
 * selling 50*1 gallons @ $300
 * <p>
 *
 * winning strategy is
 * 49*1 @ 1
 * 1*51 @ 2
 *
 * greedy strategy (without allowance to overbuy) will buy
 * 50*1 @ $1
 * 50*1 @ 300
 */
public class GreedyBuyingImpl implements BuyingStrategy {
    private static final Logger LOGGER = getLogger(GreedyBuyingImpl.class);

    @Override
    public Collection<PartialPurchase> findBids(Set<BoozeSource> sources, int gallons) throws BoozeExhaustedException {
        PartialMarketState state = getInitialMarketState(sources, gallons);
        try {
            do {
                state = greedyFill(state);
            } while (state.getGallonsToBuy() > 0);
        } catch (BoozeExhaustedException e) {
            LOGGER.warn("Exhausted", e);
            //We were not able to fulfill this buying request
            throw new BoozeExhaustedException(e);
        }

        return state.getPurchases();

    }

    private PartialMarketState getInitialMarketState(Set<BoozeSource> sources, int gallons) {
        //Sorting sources by price, lowest first
        List<BoozeSource> sourcesSortedByPrice = new LinkedList<>(sources);
        sourcesSortedByPrice.sort((source1, source2) -> Double.compare(source1.getAvgPrice(), source2.getAvgPrice()));

        return new PartialMarketState(sourcesSortedByPrice,
                Collections.emptySet(),
                gallons, gallons);
    }

    /**
     * @param marketState current state of the market
     * @return new market state
     * @throws BoozeExhaustedException market has not enough booze
     */
    private PartialMarketState greedyFill(PartialMarketState marketState) throws BoozeExhaustedException {

        if (marketState.getSources().isEmpty()) {
            throw new BoozeExhaustedException("Market has not enough booze");
        }

        List<BoozeSource> newBoozeSources = new LinkedList<>(marketState.getSources());
        BoozeSource lowestPriceSource = newBoozeSources.remove(0);

        int gallonsToBuy = lowestPriceSource.findMaxAmountToBuy(marketState.getGallonsToBuy());

        Collection<PartialPurchase> newPurchases = new ArrayList<>(marketState.getPurchases());
        if (gallonsToBuy > 0) {
            newPurchases.add(new PartialPurchase(lowestPriceSource, gallonsToBuy));
        }// if gallonsToBuy == 0, then we can not buy from this source, probably due to minConsignment restriction

        return new PartialMarketState(newBoozeSources,
                newPurchases,
                marketState.getInitialRequest(),
                marketState.getGallonsToBuy() - gallonsToBuy
        );
    }

}


// (av-min)
