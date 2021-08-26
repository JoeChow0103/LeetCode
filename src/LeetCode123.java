public class LeetCode123 {
    /*
    clarify: can overlap? can sell and buy at the same day
    (buy1,sell1) (buy2,sell2) max profit
    buy1 = max(buy1, 0-price), not buy and buy
    sell1 = max(sell1, buy1+price), not sell and sell
    buy2 = max(buy2, sell1-price), not buy and buy
    sell2 = max(sell2, buy2+price), not sell and sell
     */
    public int maxProfit(int[] prices) {
        //c.
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        for (int p : prices) {
            buy2 = Math.max(buy2, sell1-p); // buy = -minP
            sell2 = Math.max(sell2, buy2 + p);
            buy1 = Math.max(buy1, -p); // buy = -minP
            sell1 = Math.max(sell1, buy1 + p);
        }
        return Math.max(sell1,sell2);
    }
}
