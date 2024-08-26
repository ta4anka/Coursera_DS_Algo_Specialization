package module2_algorithmic_warm_up;

import java.util.Scanner;


/**
 * Given two numbers 1 ≤ n ≤ 10^14 and 2 ≤ m ≤ 10^3, it is required to find the n-th Fibonacci number modulo m.
 * For example, if the input is n = 10 and m = 2, the output should be 1 (F(10) = 55; 55 mod 2 = 1).
 */

public class FibonacciHuge {

    /**
     * Naive method to calculate Fibonacci number modulo m
     * <p>
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        // Compute Fibonacci number modulo m for the given n
        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m; // Return the result modulo m
    }

    /**
     * Optimized method to calculate Fibonacci number modulo m using Pisano Period
     * <p>
     * * Time Complexity: O(m² + n) = O(n), Space Complexity: O(1)
     *
     * @see <a href="https://en.wikipedia.org/wiki/Pisano_period">Pisano_period on wikipedia</a>
     */
    private static long getFibonacciHuge(long n, long m) {
        // Find Pisano Period length for modulo m
        long pisanoPeriodLength = pisanoPeriod(m);

        // Reduce n modulo Pisano Period length
        n = n % pisanoPeriodLength;

        // Calculate Fibonacci number modulo m up to reduced n
        if (n <= 1) {
            return n; // Base cases for n = 0 and n = 1
        }

        long previous = 0;
        long current = 1;

        // Compute Fibonacci number modulo m up to n
        for (long i = 0; i < n - 1; ++i) {
            long tmp = (previous + current) % m;
            previous = current;
            current = tmp;
        }

        return current; // Return the result modulo m
    }


    /**
     * Function to find the Pisano Period length for modulo m
     */
    private static long pisanoPeriod(long m) {
        long previous = 0;
        long current = 1;

        // Iterate to find the period length
        for (long i = 0; i < m * m; ++i) {
            long tmp = (previous + current) % m;
            previous = current;
            current = tmp;

            // Check for the start of a new Pisano Period
            if (previous == 0 && current == 1) {
                return i + 1; // Return the length of the Pisano Period
            }
        }

        return 0; // This should never be reached if m is valid
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}
