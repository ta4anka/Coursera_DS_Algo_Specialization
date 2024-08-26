package module2_algorithmic_warm_up;

import java.util.Scanner;

/**
 * Given two integers a and b, the task is to compute the least common multiple (LCM)
 * of these two numbers.
 * The LCM of two numbers is the smallest positive integer that is divisible by both numbers.
 * <p>
 * For example, given the input 4 and 5, the output should be 20.
 * <p>
 * This class implements two methods to compute the LCM:
 * 1. A naive method that iterates through all possible multiples.
 * 2. An optimized method using the relationship between LCM and GCD.
 */
public class LCM {

    /**
     * Naive method to find the least common multiple (LCM) of two integers.
     * It iterates through all possible multiples of the first integer up to a limit
     * defined by the product of the two numbers.
     * <p>
     * Time Complexity: O(a * b) - In the worst case, the loop runs up to the product of a and b.
     * Space Complexity: O(1) - Only a constant amount of space is used.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the least common multiple of a and b
     */
    private static long lcm_naive(int a, int b) {
        // Iterate through all numbers from 1 to the product of a and b
        for (long l = 1; l <= (long) a * b; ++l) {
            // If l is divisible by both a and b
            if (l % a == 0 && l % b == 0) {
                return l; // Return the smallest such number
            }
        }
        // In case no LCM is found (which theoretically should never happen)
        return (long) a * b;
    }

    /**
     * Optimized method to find the least common multiple (LCM) of two integers
     * using the relationship between LCM and GCD. The formula used is:
     * <p>
     * LCM(a, b) = |a * b| / GCD(a, b)
     * <p>
     * where GCD(a, b) is computed using the Euclidean algorithm.
     * <p>
     * Time Complexity: O(log(min(a, b))) - Time complexity of computing GCD using the Euclidean algorithm.
     * Space Complexity: O(1) - Only a constant amount of space is used.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the least common multiple of a and b
     */
    private static long lcm_optimized(int a, int b) {
        // Compute GCD using the Euclidean algorithm
        int gcd = gcd_euclidean(a, b);
        // Compute LCM using the relationship between LCM and GCD
        return (long) a * b / gcd;
    }

    /**
     * Computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b
     */
    private static int gcd_euclidean(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(lcm_optimized(a, b));
    }
}

