import java.util.HashMap;

/**
 * Created by admin on 1/8/17.
 */
public class MakeChange {
    /**
     * Ways to calculate the number ways to get to an amount given a certain denomination of coins using DP
     *
     * Time Complexity: O(S + n)
     * Space Complexity: O(S)
     */
    public int coinChange(int[] coins, int amount) {
        return coinChange(coins, amount, 0, new HashMap<String, Integer>());
    }

    public int coinChange(int[] coins, int amount, int index, HashMap<String, Integer> memo)  { //
        //start with first coin, add to that the number of ways to make it 1,2,3... n ways

        if (amount == 0) {
            return 1;
        }
        if (index >= coins.length) {
            return 0;
        }

        int numWays = 0;
        int amountWithCoin = 0;

        String key = amount + "-" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        while (amountWithCoin <= amount) {
            numWays += coinChange(coins, amount - amountWithCoin, index + 1, memo);
            amountWithCoin += coins[index];
        }
        memo.put(key, numWays);
        return numWays;
    }
}
