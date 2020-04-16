package lesson_5.my_dz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Main {

    private final List<Item> items = new ArrayList<>();


    public static void main( String[] args ) {
        int numOfItems = 5;

        Backpack bp = new Backpack(15);

        bp.makeAllSets(new Main().addItems(numOfItems));

        List<Item> bestSet = bp.getBestSet();

        System.out.println("Количество вещей в рюкзаке равно: " + bestSet.size() + "шт.");
        int totalP = 0;
        int totalW = 0;
        for (int i = 0; i < bestSet.size(); i++) {
            int w = bestSet.get(i).getWeight();
            int p = bestSet.get(i).getPrice();
            System.out.println((i + 1) + " Вещь весит: " + w + "  стоит: " + p);
            totalP += p;
            totalW += w;

        }
        System.out.println("Вес рюкзака: " + totalW);
        System.out.println("Ценность рюкзака: " + totalP);
    }

    private List<Item> addItems( int itemCount ) {
        if (itemCount == 0) return items;
        Item newItem = new Item();
        newItem.setRandomWeight(5);
        newItem.setRandomPrice(5);
        this.items.add(newItem);
        return addItems(itemCount - 1);
    }
}

public class Backpack {

    private List<Item> bestItems;
    private final int maxW;
    private int bestPrice;

    public Backpack( int maxW ) {
        this.maxW = maxW;
    }

    private int calcWeight( List<Item> items ) {
        int sumW = 0;
        for (Item i : items) {
            sumW += i.getWeight();
        }
        return sumW;
    }

    private int calcPrice( List<Item> items ) {
        int sumP = 0;
        for (Item i : items) {
            sumP += i.getPrice();
        }
        return sumP;
    }

    private void checkSet( List<Item> items ) {
        if (calcWeight(items) <= maxW) {
            int cP = calcPrice(items);
            if (bestItems == null || cP > bestPrice) {
                bestItems = items;
                bestPrice = cP;
            }

        }
    }

    public void makeAllSets( List<Item> items ) {
        if (items.size() > 0)
            checkSet(items);
        for (int i = 0; i < (items.size()); i++) {
            List<Item> newSet = new ArrayList<>();
            for (int j = 0; j < items.size() - 1; j++) {
                newSet.add(items.get(j));
            }
            makeAllSets(newSet);
        }
    }

    public List<Item> getBestSet() {
        return bestItems;
    }
}

class Item {
    private int price;
    private int weight;

    public Item() {
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public void setRandomWeight( int maxWeight ) {
        weight = getRandomNumber(maxWeight);
    }

    public void setRandomPrice( int maxPrice ) {
        price = getRandomNumber(maxPrice);
    }

    private int getRandomNumber( int max ) {
        Random r = new Random();
        return r.nextInt((max - 1) + 1) + 1;
    }
}
