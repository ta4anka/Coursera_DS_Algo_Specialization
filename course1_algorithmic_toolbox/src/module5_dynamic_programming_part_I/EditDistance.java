package module5_dynamic_programming_part_I;

import java.util.Scanner;

/**
 * Edit Distance Problem
 * Compute the edit distance between two strings.
 * <p>
 * Input: Two strings.
 * Output: The minimum number of single-symbol insertions, deletions,
 * and substitutions to transform one string into the other one.
 * <p>
 * The Edit Distance Problem has many applications in computational
 * biology, natural language processing, spell checking, and many other areas.
 * For example, biologists often compute edit distances when they search for
 * disease-causing mutations.
 * The edit distance between two strings is defined as the minimum number of single-symbol insertions,
 * deletions, and substitutions to transform one string into the other one.
 * <p>
 * Input format. Two strings consisting of lower case Latin letters, each on a separate line.
 * Output format. The edit distance between them.
 * <p>
 * Constraints. The length of both strings is at least 1 and at most 100.
 * <p>
 * Sample 1.
 * Input:
 * short
 * ports
 * <p>
 * Output:
 * 3
 * Explanation
 * The second string can be obtained from the first one by deleting s, substituting h for p, and inserting s.
 */
class EditDistance {
    /**
     * Computes the edit distance between two strings using the Levenshtein algorithm.
     * The edit distance is defined as the minimum number of single-character insertions,
     * deletions, and substitutions required to transform one string into the other.
     * Sometimes, it’s also called the Wagner-Fischer algorithm, named after the researchers
     * who formalized and optimized it for dynamic programming in 1974.
     * <p>
     * This method uses dynamic programming to efficiently calculate the edit distance
     * by constructing a 2D table (dp) where each cell dp[i][j] represents the edit distance
     * between the substrings s[0..i-1] and t[0..j-1].
     * <p>
     * The Levenshtein algorithm iterates over each character pair in the two strings
     * to build up the solution incrementally, ensuring that each sub-problem is solved
     * optimally. This approach minimizes redundant calculations and achieves a time
     * complexity of O(n * m), where n and m are the lengths of the input strings.
     * <p>
     * Usage:
     * - Useful in applications like computational biology (e.g., DNA sequence alignment),
     * natural language processing (e.g., spell checking and similarity scoring), and
     * version control (e.g., tracking changes between file versions).
     *
     * @param s The first input string.
     * @param t The second input string.
     * @return The edit distance between the input strings.
     * <p>
     * Time Complexity: O(n * m) where n and m are the lengths of the two strings.
     * Space Complexity: O(n * m) due to the DP table.
     */
    public static int EditDistance(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Initialize the DP table
        int[][] dp = new int[n + 1][m + 1];

        // Base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // Deletion from s to empty t
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // Insertion to transform empty s to t
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If characters are the same, no additional cost is needed
                int cost = (s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1;

                // Compute the minimum edit distance for this sub-problem
                int deleteCost = dp[i - 1][j] + 1;          // Deletion
                int insertCost = dp[i][j - 1] + 1;          // Insertion
                int substituteCost = dp[i - 1][j - 1] + cost; // Substitution

                // Find the minimum of these three values
                dp[i][j] = Math.min(deleteCost, Math.min(insertCost, substituteCost));
            }
        }

        // The answer is in the bottom-right cell of the DP table
        return dp[n][m];
    }


    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
        scan.close();
    }
}