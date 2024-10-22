package module3_greedy_algorithms;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Maximum Product of Two Sequences Problem
 * <p>
 * Find the maximum dot product of two sequences of numbers.
 * <p>
 * Input format. The first line contains an integer n,
 * the second one contains a sequence of integers price_1,...,price_n, the third one contains
 * a sequence of integers clicks_1,...,clicks_n.
 * <p>
 * Output format. Output the maximum value of (price_1 ·c_1 +···+price_n ·c_n),
 * where c_1,..., c_n is a permutation of clicks_1,...,clicks_n.
 * Constraints. 1 ≤ n ≤ 10^3; 0 ≤ price_i ,clicks_i ≤ 10^5 for all 1 ≤ i ≤ n.
 * <p>
 * Sample
 * Input:
 * 3
 * 2 3 9
 * 7 4 2
 * Output:
 * 79
 * => 79 = 7 · 9 + 2 · 2 + 3 · 4.
 */


public class DotProduct {
    /**
     * Calculates the maximum dot product of two sequences of integers.
     * The function sorts both sequences in non-decreasing order and then computes the dot product by
     * multiplying corresponding elements from both sequences.
     *
     * <p>
     * Time Complexity: O(n log n) due to sorting both sequences.
     * Space Complexity: O(n)  for storing the input sequences where n is the number of elements in the sequences.
     *
     * @param a an array of integers representing the first sequence
     * @param b an array of integers representing the second sequence
     * @return the maximum possible dot product of the two sequences
     */
    private static long maxDotProduct(int[] a, int[] b) {
        // Sort both arrays in non-decreasing order
        Arrays.sort(a);
        Arrays.sort(b);

        // Calculate the dot product by multiplying corresponding elements
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += (long) a[i] * (long) b[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements in the sequences
        int n = scanner.nextInt();

        // Read the first sequence of integers
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Read the second sequence of integers
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

