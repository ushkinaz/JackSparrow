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
}
