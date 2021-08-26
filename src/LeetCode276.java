public class LeetCode276 {
    /*
    (i) is ways to paint with i posts
                            (i)
                    /                       \
    use the same color;             use the different color
    |                                   |
    1*(i-1)                         (k-1)*(i-1)
    |
    if (i-2) is the different color, dp[i-2]*(k-1)
    dp[i] = dp[i-1]*(k-1) + dp[i-2]*(k-1)*1
    dp[1] = k, dp[2] = k*k
     */
    public int numWays(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return k*k;
        int dp[] = new int[n + 1];
        dp[1] = k;
        dp[2] = k*k;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }

//    public int numWays(int n, int k) {
//        if (n == 1) return k;
//        int twoPostsBack = k;
//        int onePostBack = k*k;
//        for (int i = 3; i <= n; i++) {
//            int cur = (k-1) * (onePostBack + twoPostsBack);
//            twoPostsBack = onePostBack;
//            onePostBack = cur;
//        }
//        return onePostBack;
//    }
}
