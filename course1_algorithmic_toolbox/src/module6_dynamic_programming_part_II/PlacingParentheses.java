package module6_dynamic_programming_part_II;

import java.util.Scanner;

/**
 * Maximum Value of an Arithmetic Expression Problem
 * <p>
 * Parenthesize an arithmetic expression to maximize its value.
 * Input: An arithmetic expression consisting of digits as well as plus, minus, and multiplication signs.
 * Output: Add parentheses to the expression in order to maximize its value.
 * <p>
 * Input format. The only line of the input contains a string s of length
 * 2n + 1 for some n, with symbols s0,s1,...,s2n. Each symbol at an even
 * position of s is a digit (that is, an integer from 0 to 9) while each
 * symbol at an odd position is one of three operations from {+,-,*}.
 * <p>
 * Output format. The maximum value of the given arithmetic expression
 * among all possible orders of applying arithmetic operations.
 * Constraints. 0 ≤ n ≤ 14 (hence the string contains at most 29 symbols).
 * Sample.
 * Input:
 * 5-8+7*4-8+9
 * Output:
 * 200
 * Explanation
 * 200 = (5 − ((8 + 7) × (4 − (8 + 9))))
 */
public class PlacingParentheses {

    /**
     * Computes the maximum value achievable by parenthesizing the arithmetic expression.
     * <p>
     * This method uses dynamic programming to evaluate all possible values of subexpressions.
     * We use two 2D tables, `minValues` and `maxValues`, where minValues[i][j] and maxValues[i][j]
     * represent the minimum and maximum values achievable from the subexpression between indices i and j.
     *
     * @param exp a string representing the arithmetic expression.
     * @return the maximum value of the expression when parenthesized optimally.
     * <p>
     * Time Complexity: O(n^3), where n is the number of digits in the expression.
     * Space Complexity: O(n^2), as two 2D arrays are used to store minimum and maximum values for subexpressions.
     */
    private static long getMaximValue(String exp) {
        int n = (exp.length() + 1) / 2; // Number of operands (digits)
        long[][] minValues = new long[n][n];
        long[][] maxValues = new long[n][n];

        // Initialize min and max values for each single digit
        for (int i = 0; i < n; i++) {
            minValues[i][i] = Character.getNumericValue(exp.charAt(2 * i));
            maxValues[i][i] = Character.getNumericValue(exp.charAt(2 * i));
        }

        // Fill the tables using dynamic programming
        for (int s = 1; s < n; s++) { // s is the subexpression length
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                long[] result = minAndMax(i, j, minValues, maxValues, exp);
                minValues[i][j] = result[0];
                maxValues[i][j] = result[1];
            }
        }

        return maxValues[0][n - 1]; // Maximum value for the entire expression
    }

    /**
     * Calculates the minimum and maximum values for the subexpression from index i to j.
     * <p>
     * This method considers all possible placements of parentheses by iterating over all operators
     * between the operands in the subexpression, and calculates potential values using the eval() function.
     *
     * @param i         the starting index of the subexpression.
     * @param j         the ending index of the subexpression.
     * @param minValues 2D array storing minimum values of subexpressions.
     * @param maxValues 2D array storing maximum values of subexpressions.
     * @param exp       the original arithmetic expression.
     * @return an array of two values, minimum and maximum achievable for the subexpression from i to j.
     */
    private static long[] minAndMax(int i, int j, long[][] minValues, long[][] maxValues, String exp) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {
            char op = exp.charAt(2 * k + 1); // Operator between subexpressions

            long a = eval(maxValues[i][k], maxValues[k + 1][j], op);
            long b = eval(maxValues[i][k], minValues[k + 1][j], op);
            long c = eval(minValues[i][k], maxValues[k + 1][j], op);
            long d = eval(minValues[i][k], minValues[k + 1][j], op);

            min = Math.min(min, Math.min(Math.min(a, b), Math.min(c, d)));
            max = Math.max(max, Math.max(Math.max(a, b), Math.max(c, d)));
        }

        return new long[]{min, max};
    }

    /**
     * Evaluates a simple expression given two operands and an operator.
     *
     * @param a  the first operand.
     * @param b  the second operand.
     * @param op the operator, which can be '+', '-', or '*'.
     * @return the result of the operation.
     */
    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            throw new IllegalArgumentException("Unsupported operator: " + op);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
        scanner.close();
    }
}


