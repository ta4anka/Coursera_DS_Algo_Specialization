package module1_basic_data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem Introduction
 * <p>
 * Stack is an abstract data type supporting the operations Push() and Pop(). It is not difficult to implement
 * it in a way that both these operations work in constant time. In this problem, your goal will be to implement
 * a stack that also supports finding the maximum value and to ensure that all operations still work in constant
 * time.
 * <p>
 * Problem Description
 * Task. Implement a stack supporting the operations Push(), Pop(), and Max().
 * Input Format. The first line of the input contains the number 𝑞 of queries. Each of the following 𝑞 lines
 * specifies a query of one of the following formats: push v, pop, or max.
 * Constraints. 1 ≤ 𝑞 ≤ 400 000, 0 ≤ 𝑣 ≤ 10^5.
 * Output Format. For each max query, output (on a separate line) the maximum value of the stack.
 * <p>
 * Sample.
 * Input:
 * 5
 * push 2
 * push 1
 * max
 * pop
 * max
 * Output:
 * 22
 * Explanation:
 * After the first two push queries, the stack contains elements 1 and 2. After the pop query, the element
 * 1 is removed
 */
public class StackWithMax {

    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    /**
     * Solves the problem by reading input queries and executing stack operations.
     * <p>
     * Complexity:
     * - Push: O(1)
     * - Pop: O(1)
     * - Max: O(1)
     */
    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();

        // Primary stack to hold all values
        Stack<Integer> stack = new Stack<>();
        // Auxiliary stack to keep track of maximum values
        Stack<Integer> maxStack = new Stack<>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();

            switch (operation) {
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    // Update maxStack with the new maximum
                    if (maxStack.isEmpty() || value >= maxStack.peek()) {
                        maxStack.push(value);
                    }
                    break;
                case "pop":
                    int poppedValue = stack.pop();
                    // Synchronize maxStack by popping if the popped value matches maxStack's top
                    if (poppedValue == maxStack.peek()) {
                        maxStack.pop();
                    }
                    break;
                case "max":
                    // Maximum value is always on top of maxStack
                    System.out.println(maxStack.peek());
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
