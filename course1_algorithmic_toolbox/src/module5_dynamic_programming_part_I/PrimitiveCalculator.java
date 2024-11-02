package module5_dynamic_programming_part_I;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Primitive Calculator Problem
 * <p>
 * Find the minimum number of operations needed to get a positive integer n from 1 by using only
 * three operations: add 1, multiply by 2, and multiply by 3.
 * <p>
 * Input: An integer n.
 * Output: The minimum number of operations “+1”, “×2”, and “×3” needed to get n from 1.
 * <p>
 * <p>
 * Input format. An integer n.
 * <p>
 * Output format. In the first line, output the minimum number k of operations needed to get n from 1.
 * In the second line, output a sequence of intermediate numbers.
 * That is, the second line should contain positive integers a_0,a_1,...,a_k such that a_0 = 1, a_k = n
 * and for all 1 ≤ i ≤ k, a_i is equal to either a_i−1 + 1, 2a_i−1, or 3a_i−1.
 * If there are many such sequences, output any one of them.
 * Constraints. 1 ≤ n ≤ 10^6.
 * <p>
 * Sample
 * Input:
 * 96234
 * Output:
 * 14
 * 1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
 */
public class PrimitiveCalculator {

    /**
     * Finds the optimal sequence of operations to reach the number n starting from 1.
     * It returns a list of integers representing the sequence of steps.
     * <p>
     * This method uses dynamic programming to store the minimum operations required for each number up to n.
     * Then, it traces back from n to 1 to determine the optimal sequence.
     *
     * @param n the target integer to reach from 1
     * @return a list of integers showing the path from 1 to n with the minimum number of operations
     * <p>
     * Time Complexity: O(n) - Due to iterating over each number up to n and calculating the minimum operations.
     * Space Complexity: O(n) - Requires additional space to store the minimum operations for each number up to n.
     */
    private static List<Integer> optimal_sequence(int n) {
        // Initialize the operations count array with size n + 1 to store minimum operations required for each i up to n.
        int[] minOperations = new int[n + 1];

        // Fill minOperations with the minimum steps required to reach each number.
        for (int i = 2; i <= n; i++) {
            minOperations[i] = minOperations[i - 1] + 1;
            if (i % 2 == 0) {
                minOperations[i] = Math.min(minOperations[i], minOperations[i / 2] + 1);
            }
            if (i % 3 == 0) {
                minOperations[i] = Math.min(minOperations[i], minOperations[i / 3] + 1);
            }
        }

        // Backtrack to find the sequence.
        List<Integer> sequence = new ArrayList<>();
        while (n > 0) {
            sequence.add(n);
            if (n % 3 == 0 && minOperations[n] == minOperations[n / 3] + 1) {
                n /= 3;
            } else if (n % 2 == 0 && minOperations[n] == minOperations[n / 2] + 1) {
                n /= 2;
            } else {
                n -= 1;
            }
        }

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);

        // Print the number of operations needed.
        System.out.println(sequence.size() - 1);

        // Print the sequence of steps from 1 to n.
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

