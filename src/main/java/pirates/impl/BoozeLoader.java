package pirates.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pirates.impl.beans.BoozeSource;
import pirates.impl.exceptions.BoozeException;
import pirates.impl.exceptions.BoozeParsingException;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Loads booze sources from CSV
 */
public class BoozeLoader {

    private final CSVFormat csvFormat;

    public BoozeLoader() {
        csvFormat = CSVFormat.DEFAULT.withDelimiter(';').withHeader();
    }

    /**
     * @param reader booze is nigh
     * @return unmodifiable set of booze sources
     * @throws BoozeParsingException something went wrong
     */
    public Set<BoozeSource> loadFrom(Reader reader) throws BoozeParsingException {
        Set<BoozeSource> sources = new HashSet<>();

        try {
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord boozeRecord : records) {
                BoozeSource boozeSource = new BoozeSource(
                        boozeRecord.get("Source Name"),
                        parseDouble(boozeRecord.get("Average price of gallon")), parseInt(boozeRecord.get("Size")),
                        parseInt(boozeRecord.get("Min size")),
                        parseInt(boozeRecord.get("Step size"))
                );
                sources.add(boozeSource);
            }
        } catch (IllegalArgumentException | IllegalStateException | IOException | BoozeException e) {
            throw new BoozeParsingException(e);
        }

        return Collections.unmodifiableSet(sources);
    }
}
