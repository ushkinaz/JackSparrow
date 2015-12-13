package pirates;

public class Purchase {
    /**
     * название источника в файле sources.csv
     */
    String sourceName;
    /**
     * число галлонов, купленное у этого источника
     */
    Integer numberOfGallons;
    /**
     * цена галлона у этого источника
     */
    Double priceOfGallon;

    public Purchase(String sourceName, Integer numberOfGallons, Double priceOfGallon) {
        this.sourceName = sourceName;
        this.numberOfGallons = numberOfGallons;
        this.priceOfGallon = priceOfGallon;
    }

    public String getSourceName() {
        return sourceName;
    }

    public Integer getNumberOfGallons() {
        return numberOfGallons;
    }

    public Double getPriceOfGallon() {
        return priceOfGallon;
    }
}
