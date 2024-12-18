package module2_algorithmic_warm_up;

import java.util.Scanner;

/**
 * Given an integer 1 ≤ n ≤ 40, it is required to compute the n-th Fibonacci number.
 * For example, if the input is 10, the output should be 55.
 *
 * @see <a href="https://www.cs.usfca.edu/~galles/visualization/DPFib.html">Computing Fibonacci numbers by David Galles</a>
 */
public class Fibonacci {

    /**
     * This method calculates the Fibonacci number recursively without optimization.
     * It recalculates the same Fibonacci numbers multiple times, leading to exponential time complexity.
     * <p>
     * Time Complexity: O(2^n) - Each call to calc_fib_naive generates two further calls, leading to an exponential number of calls.
     * Space Complexity: O(n) - The depth of the recursion stack can go up to n, requiring O(n) space.
     *
     * @param n the index of the Fibonacci number
     * @return the n-th Fibonacci number
     */
    private static long calc_fib_naive(int n) {
        if (n <= 1)
            return n;

        // Recursive calls for the previous two Fibonacci numbers
        return calc_fib_naive(n - 1) + calc_fib_naive(n - 2);
    }

    /**
     * This method calculates the Fibonacci number iteratively with optimization.
     * It uses a loop and stores only the last two computed Fibonacci numbers, avoiding redundant calculations.
     * <p>
     * Time Complexity: O(n) - The loop runs from 2 to n, performing linear iterations.
     * Space Complexity: O(1) - Only a constant amount of extra space is used (for prev1, prev2, and current).
     *
     * @param n the index of the Fibonacci number
     * @return the n-th Fibonacci number
     */
    private static long calc_fib(int n) {
        if (n <= 1) {
            return n;
        }

        long prev1 = 0; // Fibonacci(0)
        long prev2 = 1; // Fibonacci(1)
        long current = 0;

        // Iteratively calculate the Fibonacci number, storing only the last two results
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return current; // Returns Fibonacci(n)
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fib(n));
    }
}
