package module4_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Sorted Array Multiple Search Problem
 * <p>
 * Search multiple keys in a sorted sequence of keys.
 * <p>
 * Input: A sorted array K of distinct integers and an array Q =[q_0,...,q_m−1] of integers.
 * Output: For each qi, check whether it occurs in K.
 * <p>
 * Input format. The first two lines of the input contain an integer n and a sequence k_0 < k_1 < ... < k_n−1
 * of n distinct positive integers in increasing order.
 * The next two lines contain an integer m and m positive integers q_0,q_1,...,q_m−1.
 * Output format. For all i from 0 to m−1, output an index 0 ≤ j ≤ n−1 such that k_j = q_i, or −1, if there is no such index.
 * <p>
 * Constraints.
 * 1 ≤ n ≤ 3 · 10^4; 1 ≤ m ≤ 10^5;
 * 1 ≤ k_i ≤ 10^9 for all 0 ≤ i < n;
 * 1 ≤ q_j ≤ 10^9 for all 0 ≤ j < m.
 * <p>
 * Sample.
 * Input:
 * 5
 * 1 5 8 12 13
 * 5
 * 8 1 23 1 11
 * Output:
 * 2 0 -1 0 -1
 * Explanation:
 * Queries 8, 1, and 1 occur at positions 3, 0, and 0, respectively, while queries 23 and 11 do not occur in the sequence of keys
 */


public class BinarySearch {

    /**
     * Implements binary search to find the index of a target integer `x` within a sorted array `a`.
     * <p>
     * Binary search reduces the search space by half each time, resulting in a logarithmic time complexity.
     * This is efficient for large sorted arrays, making it suitable for scenarios with repeated searches.
     * <p>
     * Time Complexity: O(log n), where n is the length of array `a`.
     * Space Complexity: O(1), as it requires constant space for variables `left` and `right`.
     *
     * @param a the sorted array of integers to search
     * @param x the target integer to find in `a`
     * @return the index of `x` in `a`, or -1 if `x` is not found
     */
    static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length - 1;  // Initialize search bounds
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Calculate the middle index
            if (a[mid] == x) {
                return mid;  // Target found at index `mid`
            } else if (a[mid] < x) {
                left = mid + 1;  // Target is in the right half
            } else {
                right = mid - 1;  // Target is in the left half
            }
        }
        return -1;  // Target not found
    }

    /**
     * Linear search as a brute-force approach for locating `x` in array `a`.
     * This method iterates over each element to check if it matches `x`.
     *
     * <p>
     * Time Complexity: O(n), where n is the length of `a`.
     * Space Complexity: O(1).
     *
     * @param a the array of integers to search
     * @param x the target integer to find in `a`
     * @return the index of `x` in `a`, or -1 if `x` is not found
     */
    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;  // Return index if found
        }
        return -1;  // Return -1 if not found
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        // Read and populate sorted array `a`
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Read and populate query array `b`
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        // For each query in `b`, output index from binary search in `a`, or -1 if not found
        for (int i = 0; i < m; i++) {
            System.out.print(binarySearch(a, b[i]) + " ");
        }
    }

    /**
     * Helper class for efficient input parsing using buffered reading.
     * Parses input data faster than standard `Scanner`.
     */
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        /**
         * Initializes the FastScanner with the provided input stream.
         *
         * @param stream the input stream (e.g., System.in)
         */
        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Reads the next token from the input.
         *
         * @return the next token as a String
         */
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        /**
         * Parses and returns the next integer from input.
         *
         * @return the next integer in the input
         */
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
