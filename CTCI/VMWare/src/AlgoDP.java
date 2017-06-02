import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by admin on 4/29/17.
 */
public class AlgoDP {

    /**
     * Problem 1 on HW 2 of DP
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxHere = 0;
        int maxOverall = nums[0];

        for (int i : nums) {
            maxHere += i;
            if (maxHere > maxOverall) {
                maxOverall = maxHere;
            }
            if (maxHere < 0) {
                maxHere = 0;
            }
        }

        return maxOverall;
    }

    /**
     * NEED TO DISCUSS THIS PROBLEM BECAUSE WE GOT IT WRONG. Problem 2 of DP
     */

    /**
     * Problem 3. Decision problem. Is it possible to create a value v, given the denominations?
     * @param coins
     * @param amount
     * @return
     */
    public static boolean canMakeWithCoins(int[] coins, int amount) {
        //want to make a 2d array of possibilities
        //make it from 0 to amount and by coin denominations

        boolean[][] memo = new boolean[amount+1][coins.length+1];

        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j % coins[i] == 0 ||
                        memo[j][i-1] ||
                        (j - coins[i] >= 0 &&
                                memo[j-coins[i]][i]) ) {
                    memo[j][i] = true;
                }
            }
        }

        return memo[amount][coins.length];
    }

    public static int howManyCoins(int[] coins, int amount) {
        int[][] memo = new int[amount + 1][coins.length + 1];
//        Arrays.fill(memo, -1);

        for (int i = 1; i < amount; i++) {
            for (int j = 1; j < coins.length; j++) {
                if (j-i < 0) {
                    memo[j][i] = memo[j][i-1];
                } else if (j-coins[i] >= 0) {
                    memo[j][i] = Math.min(memo[j-coins[i]][i] + 1, memo[j][i-1]);
                }
            }
        }

        return memo[amount][coins.length];

    }

    public static void main(String[] args) {
        int[] coins = {1,3,5,8};

        System.out.println(howManyCoins(coins, 10));
    }

    /**
     * Problem 4. Find min number of coins to solve
     */



//    public int coinChange(int[] coins, int amount) {
//        if (coins.length == 0) {
//            return -1;
//        }
//        return coinChange(coins, amount, 0, new HashMap<String, Integer>());
//    }
//
//    public int coinChange(int[] coins, int amount, int index, HashMap<String, Integer> memo)  { //
//        //start with first coin, add to that the number of ways to make it 1,2,3... n ways
//
//        if (amount == 0) {
//            return 1;
//        }
//        if (index == coins.length) {
//            return 0;
//        }
//
//        int numWays = 0;
//        int numCoins = 0;
//        String key = coins[index] + "-" + numCoins;
//
//        if (memo.containsKey(key)) {
//            return memo.get(key);
//        }
//
//        while (coins[index] * numCoins <= amount && index < coins.length) {
//            numWays += coinChange(coins, amount - coins[index] * numCoins, index + 1, memo);
//            numCoins++;
//        }
//        memo.put(key, numWays);
//        return numWays;
//    }
}

