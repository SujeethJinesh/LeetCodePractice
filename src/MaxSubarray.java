import java.util.ArrayList;

/**
 * Created by admin on 1/13/17.
 */
public class MaxSubarray {

    public static void main(String[] args) {
        int a[] = {-2, -3, -4, -1, -2, -1, -5, -3};

        System.out.println(maxSubarray(a));
    }


    /**
     * I will be using Kadane's algorithm which is a single pass O(n) algo.
     *
     * Time Complexity: O(n)
     *
     * Space Complexty: O(1) -- no additional space used other than entered array
     *
     * @param arr
     * @return
     */
    public static int maxSubarray(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int runningSum = 0;
        int currentMax = Integer.MIN_VALUE;
//        ArrayList<Integer> list = new ArrayList<>(arr.length);

        for (int i : arr) {

            runningSum += i;

            if (runningSum > currentMax) {
                currentMax = runningSum;

            }

            if (runningSum < 0) {
                runningSum = 0;
            }

        }

        return currentMax;
    }

}
