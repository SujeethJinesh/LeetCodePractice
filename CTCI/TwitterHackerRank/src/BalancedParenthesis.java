import java.util.Stack;

/**
 * Created by admin on 11/2/16.
 */
public class BalancedParenthesis {

    public static void main(String[] args) {
        System.out.println(isBalanced("((({})))"));
    }

    public static boolean isMatchingPair(char char1, char char2) {
        if (char1 == '(' && char2 == ')') {
            return true;
        } else if (char1 == '{' && char2 == '}') {
            return true;

        } else if (char1 == '[' && char2 == ']') {
            return true;
        }
        return false;
    }

    public static boolean isOpenBracket(char char1) {
        if (char1 == '(' || char1 == '{' || char1 == '[') {
            return true;
        }
        return false;
    }

    public static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<>();

        char[] charArray = input.toCharArray();

        for (char thisChar : charArray) {
            if (isOpenBracket(thisChar)) {
                stack.push(thisChar);
            } else if (!stack.isEmpty() && !isOpenBracket(thisChar)) {
                if (isMatchingPair(stack.peek(), thisChar)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (stack.isEmpty() && !isOpenBracket(thisChar)) {
                return false;
            }
        }

        return true;
    }

}
