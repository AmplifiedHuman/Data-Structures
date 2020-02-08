package projectCode20280.exercises;

import java.util.Stack;

public class BracketMatcher {
    public static boolean checkParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }

            if ((ch == ')' || ch == ']' || ch == '}') && stack.isEmpty()) {
                isValid = false;
            } else if ((ch == ')') && (stack.peek() != '(')) {
                isValid = false;
            } else if ((ch == ']') && (stack.peek() != '[')) {
                isValid = false;
            } else if ((ch == '}') && (stack.peek() != '{')) {
                isValid = false;
            } else if ((ch == '}') || (ch == ']') || (ch == ')')) {
                stack.pop();
            }
        }
        return isValid && (stack.size() == 0);
    }

    public static void main(String[] args) {
        String[] inputs = {"[]]()()", "c[d]", "a{b[c]d}e", "a{b(c]d}e", "a[b{c}d]e}", "a{b(c) ",
                "][]][][[]][]][][[[", "(((abc))((d)))))", "(((abc))((d)))"};
        for (String s : inputs) {
            System.out.println(checkParenthesis(s));
        }

    }
}
