package module3_greedy_algorithms;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Maximum Value of the Loot Problem
 * <p>
 * Given the capacity of a backpack and the weights and costs of different items,
 * determine the maximum value of fractions of these items that can fit into the
 * backpack without exceeding its capacity.
 * <p>
 * The input consists of the backpack capacity and the details of n items, where
 * each item has a specific weight and cost. The goal is to maximize the total value
 * of the items placed in the backpack.
 * <p>
 * Input:
 * - The first line contains two integers n (1 ≤ n ≤ 10^3) and W (0 ≤ W ≤ 2 * 10^6),
 * where n is the number of items and W is the capacity of the backpack.
 * - The next n lines each contain two integers ci (0 ≤ ci ≤ 2 * 10^6) and wi (0 < wi ≤ 2 * 10^6),
 * representing the cost and weight of the i-th item, respectively.
 * <p>
 * Output:
 * - The output should be the maximum total value of items that fit into the backpack,
 * printed with at least four digits after the decimal point.
 * <p>
 * Example:
 * <p>
 * Input:
 * 3 50
 * 60 20
 * 100 50
 * 120 30
 * <p>
 * Output:
 * 180.0000
 * <p>
 * Explanation:
 * To achieve the value 180, the thief takes the entire first item and the entire third item.
 * The second item is not taken because it does not contribute to a higher value given the capacity limit.
 */


public class FractionalKnapsack {

    /**
     * Calculates the maximum value of items that can be placed in the knapsack with the given capacity.
     * This function follows a greedy approach by selecting items based on the highest value-to-weight ratio.
     *
     * <p>
     * Time Complexity: O(n log n) due to sorting the items by their value-to-weight ratio.
     * Space Complexity: O(n) where n is the number of items.
     *
     * @param capacity the total weight capacity of the knapsack
     * @param values   an array of item values
     * @param weights  an array of item weights
     * @return the maximum value that can be carried in the knapsack
     */
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;

        // Create an array of items where each item is represented by its value and weight
        ItemValue[] items = new ItemValue[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i] = new ItemValue(values[i], weights[i]);
        }

        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, (o1, o2) -> {
            return Double.compare(o2.costPerWeight, o1.costPerWeight); // Sort in descending order
        });

        /*
         * Greedy Selection:
         * Iterate through the items (starting with the highest value-to-weight ratio).
         * For each item, either take it entirely if it fits or take the fraction that fits.
         */
        for (ItemValue item : items) {
            if (capacity == 0) {
                break; // Stop if the knapsack is full
            }
            int currentWeight = item.weight;
            int currentValue = item.value;

            if (currentWeight <= capacity) {
                // Take the entire item if it fits
                value += currentValue;
                capacity -= currentWeight;
            } else {
                // Take the fraction of the item that fits
                value += currentValue * ((double) capacity / currentWeight);
                capacity = 0; // No more capacity left
            }
        }

        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of items
        int n = scanner.nextInt();
        // Read the capacity of the knapsack
        int capacity = scanner.nextInt();
        // Arrays to store the values and weights of the items
        int[] values = new int[n];
        int[] weights = new int[n];
        // Read values and weights for each item
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        // Output the maximum value that can be carried in the knapsack
        System.out.printf("%.4f\n", getOptimalValue(capacity, values, weights));
    }

    /**
     * Helper class to represent an item with its value, weight, and value-to-weight ratio.
     */
    static class ItemValue {
        int value;          // The value(costs) of the item
        int weight;
        double costPerWeight;

        /**
         * Constructor to initialize an item with its value and weight.
         *
         * @param value  the value of the item
         * @param weight the weight of the item
         */
        public ItemValue(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.costPerWeight = (double) value / weight; // Calculate the value-to-weight ratio
        }
    }
}

