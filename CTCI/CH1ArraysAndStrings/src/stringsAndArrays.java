/**
 * Created by Sujeeth on 8/22/16.
 */
public class stringsAndArrays {

    public static void main(String[] args) {
        System.out.print(isPermutation("derp", "aiod"));
    }


    /**
     * efficiency is O(n) running, O(1) space
     * @param word
     * @return
     */
    public static boolean hasUniqueCharacters(String word) {
        if (word.length() > 128) { //if the string is longer than this, it must repeat
            return false;
        }

        boolean[] char_set = new boolean[256];
        for (int i = 0; i < word.length(); i++) {
            int val = word.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * O(n) running complexity, O(1) space complexity
     * @param string
     * @return
     */
    public static String reverseString(String string) {
        char[] arr = string.toCharArray();
        int end = string.length() - 1;
        int beg = 0;

        char temp;

        while (end > beg) {
            temp = arr[beg];
            arr[beg] = arr[end];
            arr[end] = temp;

            beg++;
            end--;
        }

        return new String(arr);
    }

    /**
     *
     * @param word1
     * @param word2
     * @return
     */
    public static boolean isPermutation(String word1, String word2) {
        if (word1.length() != word2.length()) {  //if the words aren't the same length, they can't be perms of each other
            return false;
        }
        int[] charInts = new int[256];

        char[] charArr1 = word1.toCharArray();

        for (char c : charArr1) {
            charInts[c]++;
        }

        for (int i = 0; i < word2.length(); i++) {
            char c = word2.charAt(i);

            if (--charInts[c] < 0) {
                return false;
            }
        }
        return true;
    }



}
