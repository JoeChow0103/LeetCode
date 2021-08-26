public class LeetCode309 {
    /*
    buy[i] = max(buy[i-1], sell[i-2]-price[i]), not buy and buy
    sell[i] = max(sell[i-1], buy[i-1]+price[i]), not sell and sell
    improvement: buy depends on 2 variable, i-1,i-2, then use 3 variables
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1],
                    i - 2 < 0 ? -prices[i] : sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len-1];
    }
}
