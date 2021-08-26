public class LeetCode122 {
    /*
    buy = max(buy, sell-price), not buy and buy
    sell = max(sell, buy+price), not sell and sell
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int p : prices) {
            int nextSell = Math.max(sell, buy + p);
            int nextBuy = Math.max(buy, sell - p);
            sell = nextSell;
            buy = nextBuy;
        }
        return sell;
    }
}
