public class LeetCode96 {
    /*
    say n= 5
    divide to root case: 1, 2, 3, 4, 5
    because of BST
    root: 1, left 0, right 4 (number of node)
    root: 2, left 1, right 3
    root: 3, left 2, right 2
    root: 4, left 3, right 1
    root: 5, left 4, right 0
    relation: left * right = root (number of node)
    DP: read the previous calculated
    n depends on n - 1, n-1 depends on n-2,...
    dp[0] = 1, dp[1] = 1, dp[2] = dp[1]*dp[0]+dp[0]*dp[1] = 2
    dp[3] = dp[2]*dp[0]+dp[1]*dp[1]+dp[0]*dp[2] = 5
    dp[4] = dp[3]*dp[0]+dp[2]*dp[1]+dp[1]*dp[2]+dp[0]*dp[3]=14

     */
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
