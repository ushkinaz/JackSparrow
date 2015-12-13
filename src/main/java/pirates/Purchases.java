package pirates;

import java.util.TreeSet;

public class Purchases {
    /**
     * совокупное число галлонов (из условия)
     */
    Integer numberOfGallons;
    /**
     * набор покупок в разных источниках
     */
    TreeSet<Purchase> purchases;
    /**
     * средняя цена галлона по всем источникам
     */
    Double averagePriceOfGallon;

    public Purchases(Integer numberOfGallons, TreeSet<Purchase> purchases, Double averagePriceOfGallon) {
        this.numberOfGallons = numberOfGallons;
        this.purchases = purchases;
        this.averagePriceOfGallon = averagePriceOfGallon;
    }

    public Integer getNumberOfGallons() {
        return numberOfGallons;
    }

    public TreeSet<Purchase> getPurchases() {
        return purchases;
    }

    public Double getAveragePriceOfGallon() {
        return averagePriceOfGallon;
    }
}
