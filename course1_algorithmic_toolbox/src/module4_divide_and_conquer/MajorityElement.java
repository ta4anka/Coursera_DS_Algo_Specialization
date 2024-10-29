package module4_divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Majority Element Problem
 * <p>
 * Check whether a given sequence of numbers contains an element that appears more than half of the times.
 * Input: A sequence of n integers.
 * Output: 1, if there is an element that is repeated more than n/2 times, and 0 otherwise
 * <p>
 * Input format.
 * The first line contains an integer n,
 * the next one contains a sequence of n non-negative integers. a_0,...,a_n−1.
 * Output format. Output 1 if the sequence contains an element that appears more than n/2 times, and 0 otherwise.
 * Constraints. 1 ≤ n ≤ 10^5; 0 ≤ a_i ≤ 10^9 for all 0 ≤ i < n.
 * <p>
 * Sample
 * Input:
 * 5
 * 2 3 9 2 2
 * Output:
 * 1
 * Explanation:
 * 2 is the majority element.
 */
public class MajorityElement {

    /**
     * Finds the majority element in array `a` if it exists.
     * The function first determines a candidate using Boyer-Moore Voting Algorithm.
     * It then verifies if this candidate is indeed the majority.
     * <p>
     * Time Complexity: Expected 𝑂 (n log n) due to the randomized pivot selection.
     * The 3-way partitioning ensures we skip over duplicate values, making it efficient in cases with many duplicates.
     * <p>
     * Space Complexity: 𝑂 (n log n) for the recursion stack in the average case,
     * although in the worst case (non-randomized), it could go up to O(n).
     *
     * @param a the array of integers to search for a majority element
     * @return the majority element if one exists; -1 otherwise
     */
    private static int getMajorityElement(int[] a) {
        int candidate = findCandidate(a);
        return isMajority(a, candidate) ? candidate : -1;
    }

    /**
     * Determines a candidate for majority using Boyer-Moore Voting Algorithm.
     *
     * <p>
     * Time Complexity: O(n), where n is the length of `a`.
     *
     * @param a the array of integers to find a majority candidate
     * @return the candidate for majority element
     */
    private static int findCandidate(int[] a) {
        int count = 0, candidate = -1;

        for (int num : a) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * Checks if the given candidate is indeed the majority element by counting its occurrences.
     *
     * <p>
     * Time Complexity: O(n), where n is the length of `a`.
     *
     * @param a         the array of integers to verify the candidate
     * @param candidate the candidate for majority element
     * @return true if candidate is the majority element, false otherwise
     */
    private static boolean isMajority(int[] a, int candidate) {
        int count = 0;
        for (int num : a) {
            if (num == candidate) {
                count++;
            }
        }
        return count > a.length / 2;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Output 1 if there is a majority element, otherwise 0
        if (getMajorityElement(a) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    /**
     * Helper class for efficient input parsing using buffered reading.
     * Parses input data faster than standard `Scanner`.
     */
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

