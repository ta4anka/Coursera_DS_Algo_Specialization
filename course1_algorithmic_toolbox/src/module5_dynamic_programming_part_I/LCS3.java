package module5_dynamic_programming_part_I;

import java.util.Scanner;

/**
 * Longest Common Subsequence of Three Sequences Problem
 * <p>
 * Compute the maximum length of a common subsequence of three sequences.
 * Input: Three sequences.
 * Output: The maximum length of a common subsequence.
 * <p>
 * Input format. First line: n. Second line: a1,a2,...,an.
 * Third line: m. Fourth line: b_1,b_2,...,b_m. Fifth line: l. Sixth line: c_1, c_2,..., c_l.
 * Output format. p.
 * Constraints. 1 ≤ n,m,l ≤ 100; −10^9 ≤ a_i,b_i, ci ≤ 10^9.
 * <p>
 * Sample
 * Input:
 * 5
 * 8 3 2 1 7
 * 7
 * 8 2 1 3 8 10 7
 * 6
 * 6 8 3 1 4 7
 * Output:
 * 3
 * Explanation
 * One common subsequence of length 3 in this case is (8,3,7). Another one is (8,1,7).
 */
public class LCS3 {
    /**
     * Computes the length of the longest common subsequence among three sequences.
     *
     * @param a the first sequence
     * @param b the second sequence
     * @param c the third sequence
     * @return the length of the longest common subsequence
     * <p>
     * Time Complexity: O(n * m * l), where n, m, and l are the lengths of sequences a, b, and c respectively.
     * Space Complexity: O(n * m * l) for the dynamic programming table.
     */
    private static int lcs3(int[] a, int[] b, int[] c) {
        int n = a.length;
        int m = b.length;
        int l = c.length;

        // Create a 3D array to store lengths of longest common subsequence
        int[][][] dp = new int[n + 1][m + 1][l + 1];

        // Fill dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= l; k++) {
                    // If the elements are equal, increment the count from previous indices
                    if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        // If not equal, take the maximum from the three possible cases
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }
        // The length of the longest common subsequence is in dp[n][m][l]
        return dp[n][m][l];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}
