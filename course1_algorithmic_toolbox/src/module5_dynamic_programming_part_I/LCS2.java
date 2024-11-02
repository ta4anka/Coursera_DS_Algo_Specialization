package module5_dynamic_programming_part_I;

import java.util.Scanner;

/**
 * Longest Common Subsequence of Two Sequences Problem
 * The problem has applications in data comparison (e.g., diff utility,
 * merge operation in various version control systems),
 * bioinformatics (finding similarities between genes in various species), and others.
 * <p>
 * Compute the maximum length of a common subsequence of two sequences.
 * <p>
 * Input: Two sequences.
 * Output: The maximum length of a common subsequence.
 * <p>
 * Input format. First line: n. Second line: a_1,a_2,...,a_n. Third line: m. Fourth line: b_1,b_2,...,b_m.
 * Output format. p.
 * <p>
 * Constraints. 1 ≤ n,m ≤ 100; −10^9 ≤ a_i,b_i ≤ 10^9 for all i.
 * Sample
 * Input:
 * 3
 * 2 7 5
 * 2
 * 2 5
 * Output:
 * 2
 * Explanation
 * A common subsequence of length 2 is (2,5).
 */
public class LCS2 {

    /**
     * Computes the length of the longest common subsequence between two integer arrays.
     *
     * @param a the first sequence
     * @param b the second sequence
     * @return the length of the longest common subsequence
     * <p>
     * Time complexity: O(n * m), where n is the length of the first sequence and m is the length of the second sequence.
     * Space complexity: O(n * m) for the dynamic programming table.
     */
    private static int lcs2(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        // Create a 2D array to store lengths of longest common subsequence
        int[][] dp = new int[n + 1][m + 1];

        // Build the dp array in a bottom-up fashion
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // If elements match
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // If they don't match
                }
            }
        }

        // The length of the longest common subsequence is in dp[n][m]
        return dp[n][m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}