package pirates.impl;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pirates.impl.beans.BoozeSource;
import pirates.impl.beans.PartialPurchase;
import pirates.impl.exceptions.BoozeException;
import pirates.impl.exceptions.BoozeExhaustedException;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class GreedyBuyingImplTest {
    private GreedyBuyingImpl subj;

    @BeforeMethod
    public void setUp() throws Exception {
        subj = new GreedyBuyingImpl();

    }

    @Test
    public void simplest() throws Exception {
        //given
        Set<BoozeSource> sources = ImmutableSet.of(
                source(20.0, 10, 10, 10),
                source(10.0, 10, 10, 10)
        );

        //when
        Collection<PartialPurchase> purchases = subj.findBids(sources, 20);

        //then
        assertThat(purchases)
                .extracting(PartialPurchase::getGallonsBought)
                .containsExactly(10, 10);

    }

    @Test
    public void simplestOneSource() throws Exception {
        //given
        Set<BoozeSource> sources = ImmutableSet.of(
                source(10.0, 9, 3, 3),
                source(20.0, 10, 1, 1)
        );

        //when
        Collection<PartialPurchase> purchases = subj.findBids(sources, 4);

        //then
        assertThat(purchases)
                .extracting(PartialPurchase::getGallonsBought)
                .containsExactly(6);

    }

    @Test
    public void badCase() throws Exception {
        //given
        Set<BoozeSource> sources = ImmutableSet.of(
                source(1, 50, 1, 1),
                source(2.0, 51, 51, 1),
                source(300.0, 1, 1, 1)
        );

        //when
        Collection<PartialPurchase> purchases = subj.findBids(sources, 100);

        //then
        assertThat(purchases)
                .extracting(PartialPurchase::getGallonsBought)
                .containsExactly(50, 51);

        assertThat(purchases)
                .extracting("source.avgPrice")
                .containsExactly(1.0, 2.0);

    }

    @Test
    public void notEnough() throws Exception {
        //given
        Set<BoozeSource> sources = ImmutableSet.of(
                source(10.0, 9, 3, 3),
                source(20.0, 10, 1, 1)
        );

        //when
        Throwable thrown = catchThrowable(() -> subj.findBids(sources, 40));

        //then
        assertThat(thrown)
                .isInstanceOf(BoozeExhaustedException.class);

    }

    private BoozeSource source(double price, int total, int min, int step) throws BoozeException {
        // Name is formed as descriptive text
        return new BoozeSource("S:" + total + " @ " + price + " by " + min + " * x*" + step,
                price, total, min, step);
    }
}
