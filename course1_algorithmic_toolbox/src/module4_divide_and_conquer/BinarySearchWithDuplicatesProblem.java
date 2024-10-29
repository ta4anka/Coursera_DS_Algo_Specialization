package module4_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Binary Search with Duplicates Problem
 * Find the index of the first occurrence of a key in a sorted array.
 * <p>
 * Input: A sorted array of integers (possibly with duplicates) and an integer q.
 * Output: Index of the first occurrence of q in the array or “−1” if q does not appear in the array.
 * <p>
 * Input format. The first two lines of the input contain an integer n and
 * a sequence k_0 ≤ k_1 ≤ ··· ≤ k_n−1 of n positive integers in non-decreasing order.
 * The next two lines contain an integer m and m positive integers q_0,q_1,...,q_m−1.
 * Output format. For all i from 0 to m − 1, output the index 0 ≤ j ≤ n − 1 of
 * the first occurrence of qi (i.e., kj = qi) or −1, if there is no such index.
 * <p>
 * Constraints.
 * 1 ≤ n ≤ 3 · 104; 1 ≤ m ≤ 105;
 * 1 ≤ ki ≤ 109 for all 0 ≤ i < n;
 * 1 ≤ qj ≤ 109 for all 0 ≤ j < m.
 * <p>
 * Sample.
 * Input:
 * 7
 * 2 4 4 4 7 7 9
 * 4
 * 9 4 5 2
 * Output:
 * 6 1 -1 0
 */


public class BinarySearchWithDuplicatesProblem {

    /**
     * Implements binary search to find the first occurrence of a target integer `x` within a sorted array `a`.
     *
     * <p>
     * Time Complexity: O(log n), where n is the length of array `a`.
     * Space Complexity: O(1), as it requires constant space for variables `left`, `right`, and `result`.
     *
     * @param a the sorted array of integers to search, which may contain duplicates
     * @param x the target integer to find the first occurrence of in `a`
     * @return the index of the first occurrence of `x` in `a`, or -1 if `x` is not found
     */
    static int firstOccurrenceBinarySearch(int[] a, int x) {
        int left = 0, right = a.length - 1;
        int result = -1;  // Initialize result to -1 in case `x` is not found

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == x) {
                result = mid;  // Update result to the current mid
                right = mid - 1;  // Move left to find the first occurrence
            } else if (a[mid] < x) {
                left = mid + 1;  // Target is in the right half
            } else {
                right = mid - 1;  // Target is in the left half
            }
        }
        return result;  // Returns the index of the first occurrence, or -1 if not found
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

        // For each query in `b`, output index from first occurrence binary search in `a`, or -1 if not found
        for (int i = 0; i < m; i++) {
            System.out.print(firstOccurrenceBinarySearch(a, b[i]) + " ");
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



