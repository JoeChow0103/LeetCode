public class LeetCode714 {
    /*
    buy = max(buy, sell-price), not buy and buy
    sell = max(sell, buy+price-fee), not sell and sell
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int buy = -prices[0];
        int sell = 0;

        for (int p : prices) {
            int nextSell = Math.max(sell, buy + p - fee);
            int nextBuy = Math.max(buy, sell - p);
            sell = nextSell;
            buy = nextBuy;
        }
        return sell;
    }
}
