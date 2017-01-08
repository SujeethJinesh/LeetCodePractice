/**
 * Created by admin on 1/8/17.
 */
public class BuySellStock {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction
     * (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Input: [7, 1, 5, 3, 6, 4]
     * Output: 5
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
     *
     * Time Complexity: O(n) single pass
     * Space Complexity O(1)
     */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            throw new IllegalArgumentException("Must have array length of at least two.");
            //can also return 0
        }

        //want to start with min value and subtract max value from it
        int currentMin = prices[0];
        int difference = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < currentMin) {
                currentMin = prices[i];
            }
            if (prices[i] - currentMin > difference) {
                difference = prices[i] - currentMin;
            }
        }
        return difference;
    }

    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,11};
        System.out.println(maxProfit(arr));
    }
}
