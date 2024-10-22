package module3_greedy_algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Distinct Summands Problem
 * Represent a positive integer as the sum of the maximum number of pairwise distinct positive integers.
 * <p>
 * You are organizing a competition for children and have n candies to
 * give as prizes. You would like to use these candies for top k places in this
 * competition with a restriction that a higher place gets a larger number
 * of candies. To make as many children happy as possible, you need to find
 * the largest value of k for which it is possible.
 * <p>
 * Input format. An integer n.
 * Output format. In the first line, output the maximum number k such
 * that n can be represented as the sum of k pairwise distinct positive integers.
 * In the second line, output k pairwise distinct positive integers
 * that sum up to n (if there are multiple such representations, output
 * any of them).
 * Constraints. 1 ≤ n ≤ 10^9.
 * <p>
 * Sample
 * Input:
 * 8
 * Output:
 * 3
 * 1 2 5
 */
public class DifferentSummands {

    /**
     * Finds the maximum number of distinct summands that sum up to n.
     * <p>
     * This method tries to add distinct integers starting from 1,
     * and checks if adding that number is feasible given the remaining value of n.
     * Once the sum of those distinct integers approaches n, the remaining difference is assigned to the last summand.
     * <p>
     * Time Complexity: O(n) in the worst case, as we could potentially add numbers sequentially up to n.
     * Space Complexity: O(n) to store the list of distinct summands.
     *
     * @param n the total number of candies to distribute
     * @return a list of distinct integers that sum up to n
     */
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<>();
        int current = 1;

        // Add distinct numbers starting from 1 until we can't anymore
        while (n > 0) {
            if (n - current > current) {
                summands.add(current);
                n -= current;
            } else {
                // Add the remaining value as the last summand
                summands.add(n);
                break;
            }
            current++;
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

