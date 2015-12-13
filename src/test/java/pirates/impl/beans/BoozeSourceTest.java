package pirates.impl.beans;

import org.testng.annotations.Test;
import pirates.impl.BoozeBiddingException;
import pirates.impl.BoozeException;
import pirates.impl.BoozeExhaustedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BoozeSourceTest {

    private static final int INITIAL = 1000;
    private static final int NEED_FOR_PARTY = 5;
    private static final int MIN_CONSIGNMENT = 10;
    private static final int STEP_AMOUNT = 5;

    @Test
    public void deduct() throws Exception {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", 500, INITIAL, 1, 1);

        // when
        BoozeSource lootedPlace = fathersHideout.deduct(NEED_FOR_PARTY);

        // then
        assertThat(lootedPlace.getTotalAmount())
                .isEqualTo(INITIAL - NEED_FOR_PARTY);

        assertThat(fathersHideout.getTotalAmount())
                .describedAs("Initial source is not touched")
                .isEqualTo(INITIAL);
    }

    @Test
    public void priceIsPositive() throws Exception {
        // when
        Throwable thrown = catchThrowable(() -> new BoozeSource("Bad seller", 0, INITIAL, 5, 5));

        // then
        assertThat(thrown)
                .describedAs("Price should be positive")
                .isInstanceOf(BoozeException.class);
    }

    @Test
    public void shouldBeAbleToBuyAllGallons() throws Exception {
        // when
        Throwable thrown = catchThrowable(() -> new BoozeSource("Bad seller", 500, INITIAL, 999, 20));

        // then
        assertThat(thrown)
                .describedAs("Should be able to buy all gallons.  1000 total, 999 minimal, 20 increment == not able")
                .isInstanceOf(BoozeException.class);
    }

    @Test
    public void minimalSizeEqualsTotal() throws Exception {
        // when
        BoozeSource source = new BoozeSource("Good seller", 500, INITIAL, INITIAL, 200);

        // then
        assertThat(source)
                .isNotNull();
    }

    @Test
    public void minimalShouldBeBigEnough() throws Exception {
        // when
        Throwable thrown = catchThrowable(() -> new BoozeSource("Bad seller", 500, INITIAL, 10, 20));

        // then
        assertThat(thrown)
                .describedAs("Step amount should be less or equal to minimal consignment.")
                .isInstanceOf(BoozeException.class);
    }

    @Test
    public void boozeExhausted() throws BoozeException {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", 500, INITIAL, 1, 1);

        // when
        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(INITIAL * 2));

        // then
        assertThat(thrown)
                .describedAs("If you deduct more than booze source has, exception is thrown")
                .isInstanceOf(BoozeExhaustedException.class);
    }

    @Test
    public void boozeSoldInBulkOnly() throws BoozeException {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", 500, INITIAL, MIN_CONSIGNMENT, STEP_AMOUNT);

        // when
        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(MIN_CONSIGNMENT - 1));

        // then
        assertThat(thrown)
                .describedAs("Can not deduct less than minimal consignment size")
                .isInstanceOf(BoozeBiddingException.class);
    }

    @Test
    public void biddingRulesAreEnforced() throws BoozeException {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", 500, INITIAL, MIN_CONSIGNMENT, STEP_AMOUNT);

        // when
        assert STEP_AMOUNT != 1; //Otherwise the test will be false positive

        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(MIN_CONSIGNMENT + STEP_AMOUNT - 1));

        // then
        assertThat(thrown)
                .describedAs("Allowed consignment size is defined as min_consignment + n*step_amount")
                .isInstanceOf(BoozeBiddingException.class);
    }
}
