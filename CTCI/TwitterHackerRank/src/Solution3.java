import java.util.Arrays;

/**
 * Created by admin on 10/27/16.
 */
public class Solution3 {

    /**
     * Finds the max consecutive length phrase below or equal to k letters.
     *
     * Run Time Complexity: O(n)
     * Space Time Complexity: O(1)
     *
     * @param a The input array
     * @param k The number to reach
     * @return The max length of consecutive phrases
     */
    static int maxLength(int[] a, int k) {

        //Initializing values.
        int max = 0;
        int count = 0;
        int startingIndex = 0;
        int sum = 0;
        int i = 0;
        int[] solutionArray;

        //Looping through elements until we reach the end of the array.
        while (i < a.length) {
            solutionArray = maxLengthHelper(a, k, i, sum, count);

            //Getting returned array values, could have also
            //made an object and used its getters.
            count = solutionArray[0];
            sum = solutionArray[1] - a[startingIndex++];
            i = solutionArray[2];

            //Replacing old max.
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * This is a helper method that calculates the number of indexes to reach
     * or exceed k. The returned array contains count, sum, and index in the
     * first three indices.
     *
     * This is a clean and light way to transfer information from one private
     * function to the public function. We could have also created a data class
     * that would have getters and setters with those values, but for speed,
     * I chose an array.
     *
     * @param a Input array
     * @param k Number to reach
     * @param index Index to start at
     * @param sum Current running sum
     * @param count Current number of elements used
     * @return returns an array representing the count, sum, and index
     */
    private static int[] maxLengthHelper(int[] a, int k, int index, int sum, int count) {
        if (sum > k || index >= a.length) {
            return new int[]{count-1, sum, index};
        }
        while (index < a.length && sum <= k) {
            sum += a[index];
            index++;
            count++;
        }
        if (sum == k) {
            return new int[]{count, sum, index};
        }
        return new int[]{count - 1, sum, index};
    }
}
