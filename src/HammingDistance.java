/**
 * Created by admin on 1/5/17.
 */
public class HammingDistance {

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

    /*
     * This program is to get the hamming distance between any two integers.
     * Using XOR and bitcount (both O(1) ops) we can simply find the differing
     * bits, and add them to produce our result.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(y ^ x);
    }

}
