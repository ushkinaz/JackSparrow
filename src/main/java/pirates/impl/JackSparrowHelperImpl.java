package pirates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pirates.JackSparrowHelper;
import pirates.Purchases;
import pirates.impl.beans.BoozeSource;
import pirates.impl.beans.PartialPurchase;
import pirates.impl.exceptions.BoozeExhaustedException;
import pirates.impl.exceptions.BoozeParsingException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

public class JackSparrowHelperImpl implements JackSparrowHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JackSparrowHelperImpl.class);

    private BuyingStrategy buyingStrategy;
    private BoozeLoader boozeLoader;
    private PurchasesAssembler purchasesAssembler;


    public JackSparrowHelperImpl() {
        //DI should be here
        boozeLoader = new BoozeLoader();
        purchasesAssembler = new PurchasesAssembler();
        buyingStrategy = new GreedyBuyingImpl();
    }

    /**
     * @param path            Полный путь к csv файлу с ценами и количествами (sources.csv), доступными в разных местах
     * @param numberOfGallons совокупное количество галлонов, нужное Джеку
     * @return <tt>Purchases</tt> детальный совет Джеку, где и в каком количестве покупать ром.
     * {@code null} при любой ошибочной ситуации
     */
    @Override
    public Purchases helpJackSparrow(String path, int numberOfGallons) {
        Set<BoozeSource> sources;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            sources = boozeLoader.loadFrom(reader);
        } catch (IOException | BoozeParsingException e) {
            LOGGER.error("Parsing error", e);
            return null;
        }

        Collection<PartialPurchase> bids = null;
        try {
            bids = buyingStrategy.findBids(sources, numberOfGallons);
        } catch (BoozeExhaustedException e) {
            return null;
        }
        return purchasesAssembler.assemblePurchases(bids, numberOfGallons);
    }
}
