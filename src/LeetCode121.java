public class LeetCode121 {
    /*
    solution1: try to sell a stock at index i, then find the its left side min price
    Time: O(n), Space: O(1)
    solution2: 3 states: buy, sell, do nothing. It's a branches problem with 3 states
    dp[i] most profit [0,i]
    buy[i] = max(buy[i-1], 0-price[i]), not buy and buy
    sell[i] = max(sell[i-1], buy[i-1]+price[i]), not sell and sell

    solution3: improve buy[] and sell[] to buy and sell
    but need to consider, when sell, buy could be updated recently
    so switch the order, sell and buy
     */
//    public int maxProfit(int[] prices) {
//        //c.c
//        int minP = Integer.MAX_VALUE;
//        int max = 0;
//        for (int p : prices) {
//            max = Math.max(max, p - minP);
//            minP = Math.min(minP, p);
//        }
//        return max;
//    }

    public int maxProfit(int[] prices) {
        //c.
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int p : prices) {
            buy = Math.max(buy, -p); // buy = -minP
            sell = Math.max(sell, buy + p);
        }
        return sell;
    }

}
