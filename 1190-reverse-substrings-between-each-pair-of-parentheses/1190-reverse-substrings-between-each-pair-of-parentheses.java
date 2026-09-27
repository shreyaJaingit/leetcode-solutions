class Solution {
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before entering parentheses
                stack.push(current.toString());

                // Start a new string inside parentheses
                current = new StringBuilder();

            } else if (ch == ')') {

                // Reverse the current string
                current.reverse();

                // Add it to the string before '('
                current.insert(0, stack.pop());

            } else {

                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}