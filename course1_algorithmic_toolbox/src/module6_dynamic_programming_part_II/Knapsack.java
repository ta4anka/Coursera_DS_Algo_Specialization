package module6_dynamic_programming_part_II;

import java.util.Scanner;

/**
 * Maximum Amount of Gold Problem
 * <p>
 * Given a set of gold bars of various weights and a backpack that can hold at most W pounds,
 * place as much gold as possible into the backpack.
 * <p>
 * Input: A set of n gold bars of integer weights w1,...,wn and a backpack that can hold at most W pounds.
 * Output: A subset of gold bars of maximum total weight not exceeding W .
 * <p>
 * Input format. The first line of the input contains an integer W (capacity of the backpack) and the number n of gold bars.
 * The next line contains n integers w_1,...,w_n defining the weights of the gold bars.
 * Output format. The maximum weight of gold bars that fits into a backpack of capacity W .
 * <p>
 * Constraints. 1 ≤ W ≤ 10^4; 1 ≤ n ≤ 300; 0 ≤ w_1_,...,w_n ≤ 10^5.
 * Sample.
 * Input:
 * 10 3
 * 1 4 8
 * Output:
 * 9
 * Explanation
 * The sum of the weights of the first and the last bar is equal to 9.
 */
public class Knapsack {
    /**
     * Finds the maximum weight of gold that fits into a backpack of capacity W.
     * <p>
     * This method uses dynamic programming to solve the problem by filling a 2D table
     * where dp[i][j] represents the maximum weight achievable with the first i items
     * and a capacity of j.
     *
     * @param W the maximum weight the backpack can hold.
     * @param w array of weights of the gold bars.
     * @return the maximum weight of gold bars that can be placed in the backpack without exceeding its capacity.
     * <p>
     * Time Complexity: O(n * W), where n is the number of items and W is the backpack capacity.
     * Space Complexity: O(n * W), as a 2D table is used to store intermediate results.
     */
    static int optimalWeight(int W, int[] w) {
        int n = w.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = dp[i - 1][j];  // Don't include the current item
                if (w[i - 1] <= j) {  // Check if the item can fit in the current capacity
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i - 1]] + w[i - 1]);
                }
            }
        }

        return dp[n][W];  // Maximum weight that fits into the backpack
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
        scanner.close();
    }
}
