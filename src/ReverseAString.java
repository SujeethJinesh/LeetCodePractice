/**
 * Created by admin on 1/11/17.
 */
public class ReverseAString {

    /**
     * Reverse a string
     *
     * Time Complexity: O(k), k is the length of the word
     *
     * Space Complexity: O(k), k is the length of the word
     */
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Reverse a string
     *
     * Time Complexity: O(k), k is the length of the word
     *
     * Space Complexity: O(k), k is the length of the word
     */
    public static String reverseString2(String str) {
        char[] arr = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        char temp;

        while (left <= right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(reverseString("abcdefghi"));
        System.out.println(reverseString2("abcdefghi"));

    }

}
