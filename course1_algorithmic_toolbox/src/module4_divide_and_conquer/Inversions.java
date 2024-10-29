package module4_divide_and_conquer;

import java.util.Scanner;

/**
 * Number of Inversions Problem
 * Compute the number of inversions in a sequence of integers.
 * <p>
 * Input: A sequence of n integers a_1,...,a_n.
 * Output: The number of inversions in the sequence, i.e., the number of indices i < j such that a_i > a_j
 * <p>
 * Input format. The first line contains an integer n, the next one contains a sequence of integers a_1,...,a_n.
 * Output format. The number of inversions in the sequence.
 * <p>
 * Constraints. 1 ≤ n ≤ 30000, 1 ≤ a_i ≤ 10^9 for all 1 ≤ i ≤ n.
 * <p>
 * Sample.
 * Input:
 * 5
 * 2 3 9 2 9
 * Output:
 * 2
 * Explanation
 * The two inversions here are (2,4) (a_2 = 3 > 2 = a_4) and (3,4) (a_3 = 9 > 2 = a_4)
 */
public class Inversions {
    /**
     * Counts the number of inversions in the array using a modified merge sort algorithm.
     *
     * <p>
     * Time Complexity: O(n log n), where n is the length of the array.
     * This complexity arises from the divide-and-conquer approach, where we divide the array
     * into two halves (log n levels of division) and merge them in linear time O(n) at each level.
     * </p>
     *
     * <p>
     * Space Complexity: O(n) for the auxiliary temporary array used to store merged elements.
     * </p>
     *
     * <p>Summary of Complexity:</p>
     * <ul>
     *     <li>Overall Time Complexity: O(n log n)</li>
     *     <li> Space Complexity: O(n) due to the auxiliary array used in merging</li>
     * </ul>
     *
     * @param a     the original array
     * @param temp  a temporary array used for merging
     * @param left  the starting index of the subarray
     * @param right the ending index of the subarray
     * @return the number of inversions in the array
     */
    private static long getNumberOfInversions(int[] a, int[] temp, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }

        int mid = (left + right) / 2;
        // Count inversions in the left and right halves recursively
        numberOfInversions += getNumberOfInversions(a, temp, left, mid);
        numberOfInversions += getNumberOfInversions(a, temp, mid, right);

        // Count inversions during the merge process
        numberOfInversions += mergeAndCount(a, temp, left, mid, right);
        return numberOfInversions;
    }

    /**
     * Merges two sorted subarrays and counts the inversions.
     *
     * <p>
     * Time Complexity: O(n) for merging two subarrays, where n is the length of the array.
     * During the merge, each comparison that finds a right subarray element smaller than
     * a left subarray element increments the inversion count.
     * </p>
     *
     * <p>
     * Space Complexity: O(n) as it utilizes the auxiliary `temp` array to temporarily store
     * merged elements before copying them back into the main array.
     * </p>
     *
     * @param a     the original array containing the subarrays to merge
     * @param temp  a temporary array to store merged elements
     * @param left  the starting index of the left subarray
     * @param mid   the starting index of the right subarray
     * @param right the ending index of the right subarray
     * @return the number of inversions found during the merge
     */
    private static long mergeAndCount(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;    // Pointer for left subarray
        int j = mid;     // Pointer for right subarray
        int k = left;    // Pointer for the temporary array
        long inversions = 0;

        while (i < mid && j < right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                // Count inversions: All remaining elements in left subarray are greater than a[j]
                inversions += (mid - i);
            }
        }
        // Copy any remaining elements from the left subarray
        while (i < mid) {
            temp[k++] = a[i++];
        }
        // Copy any remaining elements from the right subarray
        while (j < right) {
            temp[k++] = a[j++];
        }
        // Copy merged elements back into original array
        System.arraycopy(temp, left, a, left, right - left);

        return inversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] temp = new int[n];
        System.out.println(getNumberOfInversions(a, temp, 0, a.length));
    }
}

