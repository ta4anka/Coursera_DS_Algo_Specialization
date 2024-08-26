package module2_algorithmic_warm_up;

import java.util.Scanner;

/**
 * Given a number 1 ≤ n ≤ 10^6, it is required to find the last digit of the n-th Fibonacci number.
 * For example, if the input is 3, the output should be 2 (F(3) = 2);
 * if the input is 139, the output should be 1 (F(139) = 250095301248058391139327916261).
 */
public class FibonacciLastDigit {

    /**
     * This method calculates the Fibonacci number naively and then takes the last digit at the end.
     * <p>
     * Time Complexity: O(n) - The loop runs for n-1 iterations to compute the nth Fibonacci number.
     * Space Complexity: O(1) - Only a constant amount of space is used (for previous and current variables).
     *
     * @param n the index of the Fibonacci number
     * @return the last digit of the nth Fibonacci number
     */
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        // Calculates the Fibonacci number directly
        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        // Takes the last digit after the full number is computed
        return current % 10;
    }

    /**
     * This method optimizes the calculation by taking the last digit at each step of the Fibonacci computation.
     * <p>
     * Time Complexity: O(n) - The loop runs for n-1 iterations to compute the last digit of the nth Fibonacci number.
     * Space Complexity: O(1) - Only a constant amount of space is used (for previous and current variables).
     *
     * @param n the index of the Fibonacci number
     * @return the last digit of the nth Fibonacci number
     */
    private static int getFibonacciLastDigit(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        // Calculates only the last digit of each Fibonacci number
        for (int i = 2; i <= n; ++i) {
            int newCurrent = (previous + current) % 10; // Only the last digit is kept
            previous = current;
            current = newCurrent;
        }

        return current; // Directly returns the last digit of the nth Fibonacci number
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}
