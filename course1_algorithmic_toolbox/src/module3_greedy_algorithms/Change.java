package module3_greedy_algorithms;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Money Change Problem
 * Given an integer 1 ≤ money ≤ 10^3, compute the minimum number of coins needed
 * to make change for the given amount using coins of denominations 1, 5, and 10.
 * <p>
 * Example:
 * If the input is money = 28, the output should be 6, because:
 * <p>
 * 2 coins of 10 (10 + 10 = 20)
 * 1 coin of 5 (20 + 5 = 25)
 * 3 coins of 1 (25 + 1 + 1 + 1 = 28)
 * Thus, the total number of coins needed is 2 + 1 + 3 = 6.
 */


public class Change {
    /**
     * Computes the minimum number of coins needed to make change for the given amount
     * using default coin denominations {10, 5, 1}.
     * <p>
     * This method is a specific case of the more generalized method that accepts custom denominations.
     * <p>
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param money the amount of money to make change for
     * @return the minimum number of coins needed
     */
    private static int getChange(int money) {
        Integer[] defaultCoins = {10, 5, 1};
        int coinsCounter = 0;
        for (int coin : defaultCoins) {
            coinsCounter += money / coin;
            money = money % coin;
        }
        return coinsCounter;
    }

    /**
     * Computes the minimum number of coins needed to make change for the given amount
     * using an array of custom coin denominations.
     * <p>
     * Difference from the first method:
     * - This method allows for any set of coin denominations, making it more flexible.
     * <p>
     * Time Complexity: O(n log n) (due to sorting) + O(n) for the change computation
     * Space Complexity: O(1)
     *
     * @param money the amount of money to make change for
     * @param coins an array of coin denominations available, sorted in descending order
     * @return the minimum number of coins needed
     */
    private static int getChange(int money, Integer[] coins) {
        // Ensure coins are sorted in descending order
        Arrays.sort(coins, Collections.reverseOrder());

        int coinsCounter = 0;
        for (int coin : coins) {
            coinsCounter += money / coin;
            money = money % coin;
        }
        return coinsCounter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}
