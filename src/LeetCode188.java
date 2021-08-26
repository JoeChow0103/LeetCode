public class LeetCode188 {
    /*
    buy[k][i], sell[k][i]: [0, i] kth buy/sell max profit
    buy[k][i] = max(buy[k][i-1], sell[k-1][i-1]-price[i]), not buy and buy
    sell[k][i] = max(sell[k][i-1], buy[k][i-1]+price[i]), not sell and sell
    Time: O(kn), Space: O(kn)
    improvement:
    buy[k] = max(buy[k], sell[k-1]-price[i]), not buy and buy
    sell[k] = max(sell[k], buy[k]+price[i]), not sell and sell
     */
    public int maxProfit(int k, int[] prices) {
        //c.c
        if (prices == null || prices.length == 0 || k < 1) return 0;

        int len = prices.length;
        int sell[][] = new int[k+1][len];
        int buy[][] = new int[k+1][len];
        for (int i = 1; i <= k; i++) {
            buy[i][0] = -prices[0];
            for (int j = 1; j < len; j++) {
                sell[i][j] = Math.max(sell[i][j - 1], buy[i][j - 1] + prices[j]);
                buy[i][j] = Math.max(buy[i][j - 1], sell[i - 1][j - 1] - prices[j]);
            }
        }
        return sell[k-1][len - 1];
    }
}
