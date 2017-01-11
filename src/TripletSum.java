import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by admin on 1/10/17.
 */
public class TripletSum {

    /**
     * Find the sum of 3 integers in an array that is closest to the target value
     *
     *  Time Complexity: O(n^2)
     *
     *  Space Complexity: O(n)
     *
     */

    public static boolean tripletSum(int[] arr, int sum) {
        //first we will sort the array O(n*log(n)).

        Arrays.sort(arr);

        int currentSum;
        int left = 0;
        int right = arr.length - 1;

        HashSet<Integer> hashSet = new HashSet<>();

        //use the outer loop to fix an index of the array, i, as one of the pairs

        for (int i = 0; i < arr.length; i++) {

            currentSum = sum -arr[i];

            //this is the pair sum algorithm with an updated sum

            for (int j = 0; j < arr.length; j++) {
                if (left == i) {
                    left++;
                }
                if (right == i) {
                    right--;
                }
                if (hashSet.contains(arr[left] + arr[right])) {
                    return true;
                } else {
                    hashSet.add(currentSum - (arr[left] + arr[right]));
                    if (arr[left] + arr[right] > currentSum) {
                        right--;
                    } else if (arr[left] + arr[right] < currentSum) {
                        left++;
                    } else {
                        return true;
                    }
                }
                if (left >= right) {
                    break;
                }
            }

            left = 0;
            right = arr.length - 1;
            hashSet.clear();

        }
        return false;

    }

}
