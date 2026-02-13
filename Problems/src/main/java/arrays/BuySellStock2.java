package arrays;

public class BuySellStock2 {
    /*
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
     */

    /*
    Idea is to sell till max and buy again whenever there is a dip.
    */
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int max = prices[0]; //To see the best time to have sold when a dip happens.
        int profit = 0;

        for(int price : prices) {
            if(price < max) { //Dip happens, so sell till max and buy now.
                profit += (max-buy);
                buy = price;
                max = price;
            }
            max = Math.max(max, price);
        }

        profit += (max-buy);

        return profit;
    }

    /*
    Same logic as above, just doesn't bother checking for dip. Always buys and sells as long as price keeps increasing.
    Doesn't check if this is the max that can be achieved. Profit is added at every step.
     */
    public int maxProfitAlt(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int p : prices) {
            if(p > min) {
                profit += p-min;
                min = p;
            }
            min = p;
        }
        return profit;
    }
}
