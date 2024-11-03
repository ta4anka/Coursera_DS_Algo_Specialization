package module6_dynamic_programming_part_II;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 3-Partition Problem
 * Partition a set of integers into three subsets with equal sums.
 * Input: A sequence of integers v_1,v_2,...,v_n.
 * Output: Check whether it is possible to partition them into three subsets with equal sums, i.e.,
 * check whether there exist three
 * disjoint sets S_1,S_2,S_3 ⊆ {1,2,...,n} such that S_1 U S_2 U S_3 = {1,2,...,n}
 * and Σ (i ∈ S_1) v_i = Σ (j ∈ S_2) v_j = Σ (k ∈ S_3) v_k
 * <p>
 * Three pirates are splitting their loot consisting of n items of varying value.
 * Can you help them to evenly split the loot?
 * Input format. The first line contains an integer n. The second line contains integers v_1,v_2,...,v_n
 * separated by spaces.
 * Output format. Output 1, if it is possible to partition v_1,v_2,...,v_n into three subsets with equal sums, and 0 otherwise.
 * Constraints. 1 ≤ n ≤ 20, 1 ≤ v_i ≤ 30 for all i.
 * <p>
 * Sample
 * Input:
 * 13
 * 1 2 3 4 5 5 7 7 8 10 12 19 25
 * Output:
 * 1
 * Explanation
 * 1 + 3 + 7 + 25 = 2 + 4 + 5 + 7 + 8 + 10 = 5 + 12 + 19.
 */
public class Partition3 {

    /**
     * Checks if it's possible to partition the array into three subsets with equal sums.
     * <p>
     * This method uses dynamic programming to find subsets that add up to the target sum.
     * The target sum is the total sum divided by three. We use a 3D DP array where
     * dp[i][j][k] indicates if a subset with sums i, j, and k is achievable using a subset
     * of the elements up to the current index.
     *
     * @param A an array of integers representing the items' values.
     * @return 1 if the array can be partitioned into three subsets with equal sums, otherwise 0.
     * <p>
     * Time Complexity: O(n * target^2), where n is the number of items, and target is the sum divided by 3.
     * Space Complexity: O(n * target^2), as a 3D DP array is used to track achievable sums.
     */
    private static int partition3(int[] A) {
        int sum = Arrays.stream(A).sum();

        // If the total sum is not divisible by 3, return 0 (impossible to divide equally)
        if (sum % 3 != 0) {
            return 0;
        }

        int target = sum / 3;
        int n = A.length;

        // 3D DP array where dp[i][j][k] is true if we can achieve subset sums of i, j, and k
        boolean[][][] dp = new boolean[n + 1][target + 1][target + 1];
        dp[0][0][0] = true;

        // Populate DP table
        for (int i = 1; i <= n; i++) {
            int value = A[i - 1];
            for (int j = 0; j <= target; j++) {
                for (int k = 0; k <= target; k++) {
                    // Case 1: Skip current item
                    dp[i][j][k] = dp[i - 1][j][k];

                    // Case 2: Add current item to the first subset if it fits
                    if (j >= value) {
                        dp[i][j][k] = dp[i][j][k] || dp[i - 1][j - value][k];
                    }

                    // Case 3: Add current item to the second subset if it fits
                    if (k >= value) {
                        dp[i][j][k] = dp[i][j][k] || dp[i - 1][j][k - value];
                    }
                }
            }
        }

        // Return 1 if we can achieve target sums for all three subsets, otherwise 0
        return dp[n][target][target] ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
        scanner.close();
    }
}

