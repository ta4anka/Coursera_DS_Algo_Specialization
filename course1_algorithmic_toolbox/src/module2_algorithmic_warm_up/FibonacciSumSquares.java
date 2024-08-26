package module2_algorithmic_warm_up;

import java.util.Scanner;


/**
 * Given a number n (0 ≤ n ≤ 10^18), the task is to compute the last digit of the sum of squares of the first n Fibonacci numbers.
 * For example, if the input is n = 7, the output should be 3
 * (The sum of squares of Fibonacci numbers from F(0) to F(7) * is 0^2 + 1^2 + 1^2 + 4^2 + 9^2 + 25^2 + 64^2 + 169^2 =
 * 0 + 1 + 1 + 4 + 9 + 25 + 64 + 169 = 273; the last digit of 273 is 3).
 */
public class FibonacciSumSquares {

    // The length of the Pisano period for modulo 10 is 60.
    private static final long PISANO_PERIOD_10 = 60;

    /**
     * Naive method to compute the last digit of the sum of squares of the first n Fibonacci numbers.
     * This method iteratively computes Fibonacci numbers and their squares, and sums them up.
     * <p>
     * Time Complexity: O(n), because it computes Fibonacci numbers and their squares up to n.
     * Space Complexity: O(1), as it uses a constant amount of additional space.
     *
     * @param n The number up to which Fibonacci numbers are considered.
     * @return The last digit of the sum of squares of the first n Fibonacci numbers.
     */
    private static long getFibonacciSumSquaresNaive(long n) {
        long result;
        if (n <= 1) {
            result = n;
        } else {
            long previous = 0;
            long current = 1;
            long sum = 1;
            for (long i = 0; i < n - 1; ++i) {
                long tmp_previous = previous;
                previous = current;
                current = tmp_previous + current;
                sum += current * current;
            }
            result = sum % 10;
        }
        return result;
    }

    /**
     * Computes the last digit of the n-th Fibonacci number.
     * Uses an iterative approach to calculate Fibonacci numbers modulo 10.
     * <p>
     * Time Complexity: O(1), due to the fixed size of Pisano period (maximum of 60 iterations).
     * Space Complexity: O(1), as it uses a constant amount of additional space.
     *
     * @param number The index of the Fibonacci number.
     * @return The last digit of the n-th Fibonacci number.
     */
    private static long lastDigitFibonacci(long number) {
        long lastDigit = number;
        for (long index = 2, first = 0, second = 1; index <= number; index++) {
            lastDigit = (first + second) % 10;  // Calculate the last digit of the current Fibonacci number.
            first = second;  // Move to the next Fibonacci number.
            second = lastDigit;
        }
        return lastDigit;  // Return the last digit of the n-th Fibonacci number.
    }

    /**
     * Optimized method to compute the last digit of the sum of squares of the first n Fibonacci numbers.
     * Leverages the property that the sum of squares of the first n Fibonacci numbers
     * is equal to F(n) * F(n + 1), and reduces the problem using the Pisano period.
     * <p>
     * Time Complexity: O(1), due to the fixed size of Pisano period (maximum of 60 iterations).
     * Space Complexity: O(1), as it uses a constant amount of additional space.
     *
     * @param number The index of the last Fibonacci number in the sum.
     * @return The last digit of the sum of squares of the first n Fibonacci numbers.
     */
    private static long lastDigitSumSquares(long number) {
        // Reduce the problem size using the Pisano period for modulo 10.
        long smaller = number % PISANO_PERIOD_10;
        long larger = (number + 1) % PISANO_PERIOD_10;

        // Return the last digit of the product of F(n) and F(n+1).
        return (lastDigitFibonacci(smaller) * lastDigitFibonacci(larger)) % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        System.out.println(lastDigitSumSquares(number));
    }
}




