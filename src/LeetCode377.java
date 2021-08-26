public class LeetCode377 {
    /*
    dfs(i) choose or not
    (i)
    |
    sum
    |
    (i-1)
    dp[i] is the number of ways to sum target i
    init: dp[0] = 1
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int tar = 1; tar <= target; tar++) {
            for (int n : nums) {
                if (tar >= n) {
                    dp[tar] = dp[tar] + dp[tar - n];
                }
            }
        }
        return dp[target];
    }
}
