package module4_divide_and_conquer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Speeding-up RandomizedQuickSort Problem
 * <p>
 * Sort a given sequence of numbers (that may contain duplicates) using a modification of RandomizedQuickSort
 * that works in O(n*log_n) expected time.
 * <p>
 * Input: An integer array with n elements that may contain duplicates.
 * Output: Sorted array (generated using a modification of RandomizedQuickSort) that works in O(n*log_n) expected time.
 * <p>
 * Input format. The first line of the input contains an integer n.
 * The next line contains a sequence of n integers a_0, a_1, . . , a_n−1.
 * Output format. Output this sequence sorted in non-decreasing order.
 * Constraints. 1 ≤ n ≤ 10^5; 1 ≤ a_i ≤ 10^9 for all 0 ≤ i < n.
 * Sample.
 * Input:
 * 5
 * 2 3 9 2 2
 * Output:
 * 2 2 2 3 9
 */
public class ImprovingQuickSort {
    private static Random random = new Random();

    /**
     * Partitions the array into three parts: elements less than pivot, elements equal to pivot,
     * and elements greater than pivot. This is useful for handling duplicate elements efficiently.
     *
     * @param a the array to partition
     * @param l the starting index of the partition
     * @param r the ending index of the partition
     * @return an array containing two indices, m1 and m2. All elements in a[l..m1-1] are less than the pivot,
     * all elements in a[m1..m2] are equal to the pivot, and all elements in a[m2+1..r] are greater than the pivot.
     */
    private static int[] partition3(int[] a, int l, int r) {
        int x = a[l];  // pivot element
        int m1 = l;    // boundary for elements less than pivot
        int m2 = r;    // boundary for elements greater than pivot
        int i = l;

        while (i <= m2) {
            if (a[i] < x) {
                // Swap a[i] with a[m1] and increase m1 and i
                int temp = a[i];
                a[i] = a[m1];
                a[m1] = temp;
                m1++;
                i++;
            } else if (a[i] > x) {
                // Swap a[i] with a[m2] and decrease m2
                int temp = a[i];
                a[i] = a[m2];
                a[m2] = temp;
                m2--;
            } else {
                // Move to the next element if equal to pivot
                i++;
            }
        }
        return new int[]{m1, m2};
    }

    /**
     * Sorts the array using a randomized version of QuickSort with 3-way partitioning.
     *
     * @param a the array to be sorted
     * @param l the starting index for sorting
     * @param r the ending index for sorting
     */
    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        // Randomly select a pivot and swap with the first element
        int k = random.nextInt(r - l + 1) + l;
        int temp = a[l];
        a[l] = a[k];
        a[k] = temp;

        // Use 3-way partitioning to handle duplicates
        int[] m = partition3(a, l, r);

        // Recursively sort the parts less than and greater than the pivot range
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Sort the array using RandomizedQuickSort with 3-way partitioning
        randomizedQuickSort(a, 0, n - 1);

        // Print the sorted array
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * Helper class for fast input parsing using buffered reading.
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

