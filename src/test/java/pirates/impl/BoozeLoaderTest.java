package pirates.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pirates.impl.beans.BoozeSource;

import java.io.StringReader;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class BoozeLoaderTest {
    private BoozeLoader subj;

    @BeforeMethod
    public void setUp() throws Exception {
        subj = new BoozeLoader();
    }

    @Test
    public void wrongHeader() throws Exception {
        // given
        StringReader reader = new StringReader(
                "Source Name;\n" +
                        "Mulroy;50;50.0;1;1"
        );

        // when
        Throwable thrown = catchThrowable(() -> subj.loadFrom(reader));

        // then
        assertThat(thrown).isInstanceOf(BoozeParsingException.class);
    }

    @Test
    public void wrongColumnType() throws Exception {
        // given
        StringReader reader = new StringReader(
                "Source Name;Size;Average price of gallon;Min size;Step size\n" +
                        "Mulroy;FIVE;50.0;1;1"
        );

        // when
        Throwable thrown = catchThrowable(() -> subj.loadFrom(reader));

        // then
        assertThat(thrown).isInstanceOf(BoozeParsingException.class);
    }

    @Test
    public void missingColumn() throws Exception {
        // given
        StringReader reader = new StringReader(
                "Source Name;Size;Average price of gallon;Min size;Step size\n" +
                        "Mulroy;4;50.0;1"
        );

        // when
        Throwable thrown = catchThrowable(() -> subj.loadFrom(reader));

        // then
        assertThat(thrown).isInstanceOf(BoozeParsingException.class);
    }

    @Test
    public void positive() throws Exception {
        // given
        StringReader reader = new StringReader(
                "Source Name;Size;Average price of gallon;Min size;Step size\n" +
                        "Mulroy;50;50.0;1;1\n" +
                        "Sao Feng;60;52.0;1;1\n" +
                        "Black Beard;200;55.0;1;1\n" +
                        "Davy Jones;100;51.0;100;100\n" +
                        "Hector Barbossa;300;50.5;200;100\n" +
                        "Kraken;200;54.0;200;200\n"
        );

        // when
        Set<BoozeSource> sources = subj.loadFrom(reader);

        // then
        assertThat(sources)
                .extracting("name", String.class)
                .containsOnly("Mulroy", "Sao Feng", "Black Beard", "Davy Jones", "Hector Barbossa", "Kraken");
    }
}
