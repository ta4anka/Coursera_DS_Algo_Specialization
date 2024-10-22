package module3_greedy_algorithms;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Largest Concatenate Problem
 * Compile the largest integer by concatenating the given integers
 * <p>
 * Input format. The first line of the input contains an integer n.
 * The second line contains integers a_1,...,a_n.
 * Output format. The largest number that can be composed out of a_1,...,a_n.
 * Constraints. 1 ≤ n ≤ 100; 1 ≤ a_i ≤ 10^3 for all 1 ≤ i ≤ n.
 * <p>
 * Sample
 * Input:
 * 3
 * 23 39 92
 * Output:
 * 923923
 */
public class LargestNumber {

    /**
     * Computes the largest number that can be formed by concatenating
     * the provided integers.
     *
     * <p>
     * This function sorts the integers based on a custom comparator
     * that compares the concatenated results of two numbers in both
     * possible orders (xy and yx) to determine their order in the final output.
     * The comparison is done lexicographically, meaning that the strings are compared based on
     * the Unicode values of their characters, one character at a time.
     * <p>
     * For example, when comparing the strings "3923" and "2339":
     * <ul>
     *     <li>The first characters are compared: '3' (Unicode 51) and '2' (Unicode 50).</li>
     *     <li>Since 51 > 50, the string "3923" is considered greater than "2339".</li>
     * </ul>
     * <p>
     * Time Complexity: O(n log n) due to sorting the array of strings.
     * Space Complexity: O(n) where n is the number of items.
     * </p>
     *
     * @param a an array of strings representing the integers to be concatenated
     * @return a string representing the largest number formed by concatenating the input integers
     */
    private static String largestNumber(String[] a) {
        Arrays.sort(a, (x, y) -> (y + x).compareTo(x + y)); // Sort in descending order

        // If the largest number is "0", the result should be "0"
        if (a[0].equals("0")) {
            return "0";
        }

        // Build the largest number from the sorted array
        StringBuilder result = new StringBuilder();
        for (String num : a) {
            result.append(num);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
        scanner.close();
    }
}

