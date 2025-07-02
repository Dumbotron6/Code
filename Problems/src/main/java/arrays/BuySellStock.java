package arrays;

public class BuySellStock {
    /*
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     */

    /*
    When calculated profit is greater that current profit, make that the new profit.
    When the new price is lower that the current low, make that the new low.
     */
    public int maxProfit(int[] prices) {

        int low = prices[0];
        int profit = 0;

        for(int i = 0; i < prices.length; i++) {
            if(prices[i] - low > profit)
                profit = prices[i] - low;
            if(prices[i] < low)
                low = prices[i];
        }

        return profit;

    }

    /*
    Alternatively, when low is not set(-1) or price is lower than current low.
    Make high not set(-1).
    When low found and high is greater than current high, make it new high and calculate profit.
    This is very slightly faster as we see if high is greater than current high before calculating profit.
     */
    public int maxProfitAlt(int[] prices) {

        int low, high, profit;
        low = high = -1;
        profit = 0;

        for(int i = 0; i < prices.length; i++) {
            if(low == -1 || prices[i] < prices[low]) {
                low = i;
                high = -1;
            }else if(low > -1 && (high == -1 || prices[i] > prices[high])) {
                high = i;
                if(prices[high] - prices[low] > profit)
                    profit = prices[high] - prices[low];
            }
        }

        return profit;

    }
}
