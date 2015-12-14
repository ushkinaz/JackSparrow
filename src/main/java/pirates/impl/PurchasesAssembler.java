package pirates.impl;

import pirates.Purchase;
import pirates.Purchases;
import pirates.impl.beans.PartialPurchase;

import java.util.Collection;
import java.util.TreeSet;

public class PurchasesAssembler {
    public Purchases assemblePurchases(Collection<PartialPurchase> partialPurchases, int numberOfGallons) {
        Double avgPrice;
        TreeSet<Purchase> listOfPurchases = new TreeSet<>();
        int totalCost = 0;
        int totalBought = 0;//may differ from numberOfGallons

        for (PartialPurchase partialPurchase : partialPurchases) {
            Purchase purchase = new Purchase(partialPurchase.getSource().getName(),
                    partialPurchase.getGallonsBought(),
                    partialPurchase.getSource().getAvgPrice());
            listOfPurchases.add(purchase);

            totalCost += partialPurchase.getGallonsBought() * partialPurchase.getSource().getAvgPrice();
            totalBought += partialPurchase.getGallonsBought();
        }

        avgPrice = (double) totalCost / totalBought;

        return new Purchases(numberOfGallons, listOfPurchases, avgPrice);
    }
}
