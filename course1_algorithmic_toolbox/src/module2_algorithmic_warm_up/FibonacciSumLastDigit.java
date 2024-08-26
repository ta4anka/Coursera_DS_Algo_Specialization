package module2_algorithmic_warm_up;

import java.util.Scanner;

/**
 * Given a number 0 ≤ n ≤ 10^18, the task is to compute the last digit of the sum of the first n Fibonacci numbers.
 * For example, if the input is n = 7, the output should be 0
 * (The sum of the first 7 Fibonacci numbers is 0+1+1+2+3+5+8 = 20; the last digit of 20 is 0).
 */
public class FibonacciSumLastDigit {

    /**
     * Naive method to compute the last digit of the sum of Fibonacci numbers up to n.
     * <p>
     * Time Complexity: O(n) - The loop runs for n-1 iterations to compute the sum of the first n Fibonacci numbers.
     * Space Complexity: O(1) - Only a constant amount of space is used (for previous, current, and sum variables).
     *
     * @param n the number of Fibonacci numbers to sum
     * @return the last digit of the sum of the first n Fibonacci numbers
     */
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        // Compute the sum of Fibonacci numbers up to n
        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10; // Return the last digit of the sum
    }

    /**
     * Optimized method to compute the last digit of the sum of Fibonacci numbers up to n.
     * <p>
     * Time Complexity: O(1) - The loop runs for a constant number of iterations, up to the Pisano period length, which is 60 for modulo 10.
     * Space Complexity: O(1) - Only a constant amount of space is used (for previous, current, sum, and Pisano period length).
     *
     * @param n the number of Fibonacci numbers to sum
     * @return the last digit of the sum of the first n Fibonacci numbers
     */
    private static long getFibonacciSumLastDigit(long n) {
        // Pisano period for modulo 10 (last digit) is known to be 60
        long pisanoPeriodLength = pisanoPeriod(10);

        // Reduce n modulo Pisano Period length
        n = n % pisanoPeriodLength;

        if (n <= 1) {
            return n; // Base cases for n = 0 and n = 1
        }

        long previous = 0;
        long current = 1;
        long sum = 1;

        // Compute the sum of Fibonacci numbers up to reduced n
        for (long i = 0; i < n - 1; ++i) {
            long tmp = (previous + current) % 10;
            previous = current;
            current = tmp;
            sum = (sum + current) % 10;
        }

        return sum;
    }

    /**
     * Function to find the Pisano Period length for modulo m.
     * <p>
     * Time Complexity: O(m^2) - The loop runs for up to m^2 iterations to find the Pisano period.
     * Space Complexity: O(1) - Only a constant amount of space is used (for previous and current variables).
     *
     * @param m the modulus for which the Pisano period length is to be found
     * @return the length of the Pisano period for modulo m
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
        long s = getFibonacciSumLastDigit(n);
        System.out.println(s);
    }
}


