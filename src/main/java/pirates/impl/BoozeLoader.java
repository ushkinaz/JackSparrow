package pirates.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pirates.impl.beans.BoozeSource;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Loads booze sources from CSV
 */
public class BoozeLoader {

    private CSVFormat csvFormat;

    public BoozeLoader() {
        csvFormat = CSVFormat.DEFAULT.withDelimiter(';');
    }

    /**
     * @param path
     * @return unmodifiable set of booze sources
     * @throws IOException
     */
    public Set<BoozeSource> loadFrom(String path) throws IOException {
        Set<BoozeSource> sources = new HashSet<>();

        Reader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("UTF-8"));
        Iterable<CSVRecord> records = csvFormat.parse(reader);
        for (CSVRecord boozeRecord : records) {
            BoozeSource boozeSource = new BoozeSource(
                    boozeRecord.get("Source Name"),
                    parseInt(boozeRecord.get("Size")),
                    parseDouble(boozeRecord.get("Average price of gallon")),
                    parseInt(boozeRecord.get("Min size")),
                    parseInt(boozeRecord.get("Step size"))
            );
            sources.add(boozeSource);
        }
        return Collections.unmodifiableSet(sources);
    }
}
