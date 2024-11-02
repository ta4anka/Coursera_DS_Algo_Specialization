package module5_dynamic_programming_part_I;

import java.util.Scanner;

/**
 * Money Change Again Problem
 * <p>
 * As we already know, a natural greedy strategy for the change problem
 * does not work correctly for any set of denominations. For example, for
 * denominations 1, 3, and 4, the greedy algorithm will change 6 cents using
 * three coins (4 + 1 + 1) while it can be changed using just two coins (3 + 3).
 * Your goal now is to apply dynamic programming for solving the Money Change Problem
 * for denominations 1, 3, and 4.
 * <p>
 * Input format. Integer money.
 * Output format. The minimum number of coins with denominations 1, 3, and 4 that changes money.
 * Constraints. 1 ≤ money ≤ 10^3.
 * <p>
 * Sample.
 * Input:
 * 34
 * Output:
 * 9
 * Explanation
 * 34 = 3 + 3 + 4 + 4 + 4 + 4 + 4 + 4 + 4
 */
public class ChangeDP {

    /**
     * Calculates the minimum number of coins required to change a given amount of money using denominations 1, 3, and 4.
     *
     * <p>
     * The method employs dynamic programming to store intermediate results
     * in an array, where each index represents the minimum coins required for that amount.
     * For each amount up to m, it checks the minimum number of coins required by considering
     * each denomination (1, 3, and 4).
     * <p>
     * Unlike the greedy approach, this dynamic programming solution ensures an optimal result for any amount.
     * <p>
     * Time Complexity: O(m) where m is the amount of money.
     * Space Complexity: O(m) due to the storage array used for intermediate results.
     *
     * @param m the amount of money to change
     * @return the minimum number of coins needed to make up the amount m
     */
    private static int getChange(int m) {
        // Array to store the minimum number of coins for each amount up to m
        int[] minCoins = new int[m + 1];

        // Base case: 0 coins are needed to make 0 amount
        minCoins[0] = 0;

        // Loop to fill minCoins array for each amount from 1 to m
        for (int i = 1; i <= m; i++) {
            minCoins[i] = Integer.MAX_VALUE;

            // Check for each denomination and update minCoins[i] accordingly
            if (i >= 1) minCoins[i] = Math.min(minCoins[i], minCoins[i - 1] + 1);
            if (i >= 3) minCoins[i] = Math.min(minCoins[i], minCoins[i - 3] + 1);
            if (i >= 4) minCoins[i] = Math.min(minCoins[i], minCoins[i - 4] + 1);
        }

        return minCoins[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

