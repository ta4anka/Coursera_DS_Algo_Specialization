//package module2_algorithmic_warm_up;

import java.util.Scanner;

/**
 * Given two integers a and b, the task is to compute the greatest common divisor (GCD)
 * of these two numbers.
 * The GCD of two numbers is the largest integer that divides both of them without leaving a remainder.
 * For example, given the input 8 and 12, the output should be 4.
 * <p>
 * This class implements two methods to compute the GCD:
 * 1. A naive method that checks all possible divisors.
 * 2. An optimized method using the Euclidean algorithm.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Greatest_common_divisor">Greatest common divisor on Wikipedia</a>
 */
public class GCD {

    /**
     * Naive method to find the greatest common divisor (GCD) of two integers.
     * It iterates through all possible divisors from 2 to the smaller of the two numbers.
     * <p>
     * Time Complexity: O(min(a, b)) - In the worst case, the loop runs from 2 to min(a, b).
     * Space Complexity: O(1) - Only a constant amount of space is used.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     */
    private static int gcd_naive(int a, int b) {
        int current_gcd = 1;
        // Iterate through all numbers from 2 to the minimum of a and b
        for (int d = 2; d <= a && d <= b; ++d) {
            // If d divides both a and b
            if (a % d == 0 && b % d == 0) {
                // Update current_gcd if d is greater than the current value
                if (d > current_gcd) {
                    current_gcd = d;
                }
            }
        }
        return current_gcd;
    }

    /**
     * Optimized method to find the greatest common divisor (GCD) of two integers
     * using the Euclidean algorithm. The algorithm is based on the principle that
     * the GCD of two numbers also divides their difference.
     * <p>
     * Time Complexity: O(log(min(a, b))) - The number of iterations is proportional
     * to the logarithm of the smaller number.
     * Space Complexity: O(1) - Only a constant amount of space is used.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     */
    private static int gcd_euclidean(int a, int b) {
        // Continue the loop until b becomes zero
        while (b != 0) {
            // Calculate remainder
            int remainder = a % b;
            // Update a and b for the next iteration
            a = b;
            b = remainder;
        }
        // The GCD is stored in a when b becomes zero
        return a;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(gcd_euclidean(a, b));
    }
}
