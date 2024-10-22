import java.util.Arrays;
import java.util.Comparator;

class Item {
    double value;
    double weight;

    Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(Item[] items, double capacity) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double ratio1 = a.value / a.weight;
                double ratio2 = b.value / b.weight;
                return Double.compare(ratio2, ratio1);
            }
        });

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity <= 0) {
                break;
            }
            if (item.weight <= capacity) {
                // Take the whole item
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                // Take the fractional part of the item
                totalValue += item.value * (capacity / item.weight);
                capacity = 0; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };

        double capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
