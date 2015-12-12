package pirates.impl.beans;

import org.testng.annotations.Test;
import pirates.impl.BoozeBiddingException;
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
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", INITIAL, 0, 1, 1);

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
    public void boozeExhausted() {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", INITIAL, 0, 1, 1);

        // when
        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(INITIAL * 2));

        // then
        assertThat(thrown)
                .describedAs("If you deduct more than booze source has, exception is thrown")
                .isInstanceOf(BoozeExhaustedException.class);
    }

    @Test
    public void boozeSoldInBulkOnly() {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", INITIAL, 0, MIN_CONSIGNMENT, STEP_AMOUNT);

        // when
        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(MIN_CONSIGNMENT - 1));

        // then
        assertThat(thrown)
                .describedAs("Can not deduct less than minimal consignment size")
                .isInstanceOf(BoozeBiddingException.class);
    }

    @Test
    public void biddingRulesAreEnforced() {
        // given
        BoozeSource fathersHideout = new BoozeSource("Fathers hideout", INITIAL, 0, MIN_CONSIGNMENT, STEP_AMOUNT);

        // when
        assert STEP_AMOUNT != 1; //Otherwise the test will be false positive

        Throwable thrown = catchThrowable(() -> fathersHideout.deduct(MIN_CONSIGNMENT + STEP_AMOUNT - 1));

        // then
        assertThat(thrown)
                .describedAs("Allowed consignment size is defined as min_consignment + n*step_amount")
                .isInstanceOf(BoozeBiddingException.class);
    }
}
