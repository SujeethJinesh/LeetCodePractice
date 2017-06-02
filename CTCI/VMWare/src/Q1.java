import java.util.Stack;

/**
 * Created by admin on 12/30/16.
 */
public class Q1 {

    public static void main(String[] args) {
        System.out.println(counting("0011000011"));
    }

    static int counting(String s) {

        if (s.length() < 2) {
            return 0;
        }

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        int pointer = 1;
        int count = 0;
        int origPointer = 1;

        while (pointer < s.length()) {
            while (!stack.isEmpty() && pointer < s.length()) {
                if (stack.peek().equals(s.charAt(pointer))) {
                    System.out.println("EQUAL\nstack: " + stack.size() + "\npointer: " + pointer + "\norigPointer: "+origPointer + "\ncount: " + count + "\n");
                    stack.push(s.charAt(pointer));
                    if (++pointer == s.length()) {
                        return count;
                    }
                } else {
                    stack.pop();
                    count++;
                    origPointer = pointer++;
                    System.out.println("POP\nstack: " + stack.size() + "\npointer: " + pointer + "\norigPointer: "+origPointer + "\ncount: " + count + "\n");

                    while (!stack.isEmpty() && pointer < s.length()) {
                        if (stack.peek().equals(s.charAt(pointer))) {
                            System.out.println("POP_EQUAL\nstack: " + stack.size() + "\npointer: " + pointer + "\norigPointer: "+origPointer + "\ncount: " + count + "\n");
                            stack = new Stack<>();
                            stack.push(s.charAt(origPointer));
                            break;
                        } else {
                            stack.pop();
                            //count++;
                            System.out.println("POP_NOT_EQUAL\nstack: " + stack.size() + "\npointer: " + pointer + "\norigPointer: "+origPointer + "\ncount: " + count + "\n");
                        }
                    }

                }
            }
            stack.push(s.charAt(origPointer));
        }
        return count;
    }

}
