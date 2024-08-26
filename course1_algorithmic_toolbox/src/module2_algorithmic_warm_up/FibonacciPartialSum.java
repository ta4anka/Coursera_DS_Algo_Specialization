package module2_algorithmic_warm_up;

import java.util.Scanner;


/**
 * Given two numbers 0 ≤ from ≤ to ≤ 10^14, the task is to compute the last digit of the sum
 * of Fibonacci numbers from F(from) to F(to), inclusive.
 * For example, if the input is from = 3 and to = 7, the output should be 1
 * (The sum of F(3) to F(7) is 2 + 3 + 5 + 8 + 13 = 31; the last digit of 31 is 1).
 */

public class FibonacciPartialSum {

    /**
     * Naive method to calculate the last digit of the sum of Fibonacci numbers from 'from' to 'to'
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;
        long current = 0;
        long next = 1;
        // Iterate through the Fibonacci sequence and accumulate the sum of values between 'from' and 'to'
        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            // Move to the next Fibonacci number
            long new_current = next;
            next = next + current;
            current = new_current;
        }
        // Return the last digit of the sum
        return sum % 10;
    }

    /**
     * Optimized method to calculate the last digit of the sum of Fibonacci numbers from 'from' to 'to'
     * Time Complexity: O(1), Space Complexity: O(1)
     */
    private static long getFibonacciPartialSum(long from, long to) {
        if (to < from) return 0;

        // Handle the case when both 'from' and 'to' are 0
        if (from == 0 && to == 0) return 0;

        // Helper function to get the last digit of the sum of Fibonacci numbers up to n
        long sumTo = getFibonacciSum(to);
        long sumFromMinusOne = getFibonacciSum(from - 1);

        // Return the difference modulo 10, adjusted for possible negative result
        return (sumTo - sumFromMinusOne + 10) % 10;
    }

    /**
     * Function to calculate the last digit of the sum of Fibonacci numbers up to n using Pisano period
     */
    private static long getFibonacciSum(long n) {
        if (n < 0) return 0; // If n is negative, sum should be 0

        // Pisano period length for modulo 10 is 60
        long pisanoPeriod = 60;
        n = n % pisanoPeriod;

        if (n == 0) return 0;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 1; i < n; ++i) { // From 1 to n-1
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10; // Keep only last digit
            sum = (sum + current) % 10; // Keep only last digit of sum
        }

        return sum;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

