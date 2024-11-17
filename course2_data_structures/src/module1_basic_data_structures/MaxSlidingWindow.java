package module1_basic_data_structures;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * Problem Introduction
 * Given a sequence 𝑎1, . . . , 𝑎𝑛 of integers and an integer 𝑚 ≤ 𝑛, find the maximum among {𝑎𝑖, . . . , 𝑎𝑖+𝑚−1} for
 * every 1 ≤ 𝑖 ≤ 𝑛 − 𝑚 + 1. A naive 𝑂(𝑛𝑚) algorithm for solving this problem scans each window separately.
 * Your goal is to design an 𝑂(𝑛) algorithm.
 * Problem Description
 * Input Format. The first line contains an integer 𝑛, the second line contains 𝑛 integers 𝑎1, . . . , 𝑎𝑛 separated
 * by spaces, the third line contains an integer 𝑚.
 * Constraints. 1 ≤ 𝑛 ≤ 10^5, 1 ≤ 𝑚 ≤ 𝑛, 0 ≤ 𝑎𝑖 ≤ 10^5 for all 1 ≤ 𝑖 ≤ 𝑛.
 * Output Format. Output max{𝑎𝑖, . . . , 𝑎𝑖+𝑚−1} for every 1 ≤ 𝑖 ≤ 𝑛 − 𝑚 + 1.
 * <p>
 * Sample.
 * Input:
 * 8
 * 2 7 3 1 5 2 6 2
 * 4
 * Output:
 * 7 7 5 6 6
 */
public class MaxSlidingWindow {

    static class FastScanner {
        BufferedReader in;
        StringTokenizer tok = new StringTokenizer("");

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements()) {
                tok = new StringTokenizer(in.readLine());
            }
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    /**
     * Computes the maximum value for each sliding window of size `m` in the array `a`.
     *
     * @param n The length of the array.
     * @param a The input array of integers.
     * @param m The size of the sliding window.
     * @return An array containing the maximum of each sliding window.
     */
    public int[] slidingWindowMaximum(int n, int[] a, int m) {
        int[] result = new int[n - m + 1]; // To store the maximums for each window
        Deque<Integer> deque = new ArrayDeque<>(); // Stores indices of array elements

        for (int i = 0; i < n; i++) {
            // Remove indices of elements no longer in the current window
            if (!deque.isEmpty() && deque.peekFirst() < i - m + 1) {
                deque.pollFirst();
            }

            // Remove indices of elements smaller than the current element
            // as they will not be the maximum in any current or future window
            while (!deque.isEmpty() && a[deque.peekLast()] <= a[i]) {
                deque.pollLast();
            }
            // Add the current element's index to the deque
            deque.offerLast(i);
            // Add the maximum for the current window to the result
            if (i >= m - 1) {
                result[i - m + 1] = a[deque.peekFirst()];
            }
        }
        return result;
    }

    /**
     * Reads input, solves the sliding window maximum problem, and prints the results.
     */
    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();

        // Get the sliding window maximums
        int[] result = slidingWindowMaximum(n, a, m);

        for (int max : result) {
            System.out.print(max + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        new MaxSlidingWindow().solve();
    }
}

