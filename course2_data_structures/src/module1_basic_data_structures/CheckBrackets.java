package module1_basic_data_structures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;


/**
 * This class checks if the brackets in a given string are balanced and properly nested.
 * <p>
 * Input Format:
 * A single string consisting of various characters, including brackets []{}(). E.g. '{[]}()', ' foo(bar[i)'
 * <p>
 * Output Format:
 * - "Success" if all brackets are correctly matched and nested.
 * - 1-based index of the first unmatched closing or opening bracket otherwise.
 * <p>
 * Time Complexity: O(n), where n is the length of the input string.
 * Space Complexity: O(n), for storing the unmatched opening brackets in the stack.
 */
public class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket
                opening_brackets_stack.push(new Bracket(next, position + 1));
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket
                if (opening_brackets_stack.isEmpty()) {
                    // Unmatched closing bracket
                    System.out.println(position + 1);
                    return;
                }

                Bracket top = opening_brackets_stack.pop();
                if (!top.Match(next)) {
                    // Unmatched pair
                    System.out.println(position + 1);
                    return;
                }
            }
        }
        // Check for any unmatched opening brackets left in the stack
        if (!opening_brackets_stack.isEmpty()) {
            System.out.println(opening_brackets_stack.peek().position);
        } else {
            System.out.println("Success");
        }
    }
}

/**
 * This class represents a Bracket with a specific type and position.
 * It also contains a method to check if it matches a given closing bracket.
 */
class Bracket {
    char type;
    int position;

    /**
     * Constructor to initialize the bracket type and its position.
     *
     * @param type     The character representing the bracket type ('(', '{', '[').
     * @param position The position of the bracket in the string (1-based index).
     */
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    /**
     * Method to check if the current bracket matches with a given closing bracket.
     *
     * @param c The closing bracket character to match.
     * @return True if the types match, otherwise false.
     */
    boolean Match(char c) {
        if (this.type == '[' && c == ']') return true;
        if (this.type == '{' && c == '}') return true;
        if (this.type == '(' && c == ')') return true;
        return false;
    }
}