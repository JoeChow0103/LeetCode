public class LeetCode279 {
    /*
    dfs(i), min # of squre sum
    (i)
    |
    min
    |
    (i-j), j is squred number
    dp[i] = min(dp[i], dp[i - j^2] + 1)
    init:dp[0] = 0, dp[1] = 1, dp[i] = Integer.MAX_VALUE
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) dp[i] = Integer.MAX_VALUE;

        for (int val = 1; val <= n; val++) {
            for (int i = 1; i * i <= val; i++) {
               dp[val] = Math.min(dp[val], dp[val - i * i] + 1);
            }
        }
        return dp[n];
    }
}
