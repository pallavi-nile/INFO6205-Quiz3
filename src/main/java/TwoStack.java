import java.util.Stack;


/*
This class implements a two-stack algorithm for evaluating arithmetic expressions.

Key components:
1. Two stacks: 
   - ops: for storing operators
   - vals: for storing numeric values

2. The evaluate method:
   - Takes a string input representing an arithmetic expression
   - Returns the result as a double

Algorithm steps:
1. Split the input string into tokens (numbers, operators, parentheses)
2. Iterate through each token:
   - If it's an opening parenthesis '(', ignore it
   - If it's an operator (+, -, *, /), push onto ops stack
   - If it's a closing parenthesis ')', perform the calculation:
     a. Pop the top operator from ops
     b. Pop the required number of values from vals
     c. Perform the operation
     d. Push the result back onto vals
   - If it's a number, parse it to double and push onto vals stack

3. After processing all tokens, the final result will be the only item left on vals stack

Important considerations:
- Use .equals() for string comparisons, not ==
- Be careful with the order of operands when popping for binary operations
- Consider adding checks for empty stacks to prevent errors

This implementation provides O(n) time complexity and effectively handles 
operator precedence through the use of parentheses in the input expression.
*/



public class TwoStack {
    Stack<String> ops = new Stack<>();
    Stack<Double> vals = new Stack<>();

    public double evaluate(String s) {
        String[] tokens = s.split(" ");

        for (String token : tokens) {
            if (token.equals("(")) {
                // Ignore opening parentheses
                continue;
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                // Push operators onto the ops stack
                ops.push(token);
            } else if (token.equals(")")) {
                // When encountering a closing parenthesis, perform the calculation
                String op = ops.pop();
                double val2 = vals.pop();
                double val1 = vals.pop();

                switch (op) {
                    case "+":
                        vals.push(val1 + val2);
                        break;
                    case "-":
                        vals.push(val1 - val2);
                        break;
                    case "*":
                        vals.push(val1 * val2);
                        break;
                    case "/":
                        vals.push(val1 / val2);
                        break;
                }
            } else {
                // Assume the token is a number and push it onto the vals stack
                vals.push(Double.parseDouble(token));
            }
        }

        // The final result should be the only value left in the vals stack
        return vals.pop();
    }
}
