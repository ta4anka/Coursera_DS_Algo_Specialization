package week1_programming_challenges;

import java.util.Scanner;

/**
 * Maximum Pairwise Product Problem
 * <p>
 * Find the maximum product of two distinct numbers in a sequence of non-negative integers.
 * <p>
 * Input: An integer n and a sequence of n non-negative integers.
 * Output: The maximum value that can be obtained by multiplying two different elements from the sequence.
 */
public class MaxPairwiseProduct {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }
            System.out.println(getMaxPairwiseProductFast(numbers));
        }
    }

    // O(n^2): go through all possible pairs and select the maximum product.
    static long getMaxPairwiseProduct(int[] numbers) {
        long result = 0;
        int n = numbers.length;
        // i - first, j - second
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
//                result = Math.max(result, (long) numbers[i] * numbers[j]);
                if ((long) numbers[i] * numbers[j] > result) {
                    result = (long) numbers[i] * numbers[j];
                }
            }
        }
        return result;
    }

    /* O(n): To find the maximum product of two numbers from our sequence,
     it is actually enough to find two maximal numbers in our sequence.*/
    static long getMaxPairwiseProductFast(int[] numbers) {
        int nSize = numbers.length;
        int max_idx1 = -1;
        for (int i = 0; i < nSize; i++) {
            if ((max_idx1 == -1) || (numbers[i] > numbers[max_idx1])) {
                max_idx1 = i;
            }
        }

        int max_idx2 = -1;
        for (int j = 0; j < nSize; j++) {
            if ((j != max_idx1) && ((max_idx2 == -1) || (numbers[j] > numbers[max_idx2]))) {
                max_idx2 = j;
            }
        }
        return (long) numbers[max_idx1] * numbers[max_idx2];
    }

}

