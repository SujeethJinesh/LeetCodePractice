/**
 * Created by admin on 2/4/17.
 */
public class EqualSubarray {

    public static void main(String[] args) {

        int[] arr = {1, 5, 5, 5, 4};

        System.out.println(equalSubarrayRecursive(arr));
        System.out.println(equalSubarrayDP(arr));
    }

    public static boolean equalSubarrayRecursive(int[] arr) {
        int sum = getSum(arr);

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum/2;
        int[] memo = new int[arr.length];

        return helper(arr, target, arr.length - 1, memo);

    }

    private static boolean helper(int[] arr, int target, int size, int[] memo) {
        if (target == 0) {
            return true;
        }

        if (size == 0 && target != 0) {
            return false;
        }

        if (arr[size-1] > target) {
            return helper(arr, target, size-1, memo);
        }

        return (helper(arr, target, size - 1, memo) ||
                helper(arr, target - arr[size - 1], size - 1, memo));
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for (int item : arr) {
            sum += item;
        }
        return sum;
    }

    public static boolean equalSubarrayDP(int[] arr) {
        int sum = getSum(arr);

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum/2;
        //want to create a matrix much like knapsack
        boolean[][] memo = new boolean[target+1][arr.length+1];

        //go through the first column because making 0 from any subset is possible
        for (int i = 0; i <= arr.length; i++)
            memo[0][i] = true;

        // initialize leftmost column, except part[0][0], as 0
        for (int i = 1; i <= sum/2; i++)
            memo[i][0] = false;

        for (int i = 1; i < target; i++) {
            for (int j = 1; j < arr.length; j++) {
                memo[i][j] = memo[i][j-1];
                if (i >= arr[j-1]) {
                    memo[i][j] = memo[i][j] ||
                            memo[i - arr[j-1]][j-1];
                }
            }
        }

        return memo[target][arr.length];
    }
}
