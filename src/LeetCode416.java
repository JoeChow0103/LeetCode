public class LeetCode416 {
    /*
    dp[i][j]
    |
    if sum j can be formed with nums[i], without nums[i]
                            |              |
                        dp[i-1][j]       dp[i-1][j-1]
    dp[i][j] is true/false if j can be formed by nums[0] to nums[i]
    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
    init
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;

        int halfSum = sum / 2;
        boolean dp[][] = new boolean[len + 1][halfSum + 1];
        dp[0][0] = true;
        for (int i = 1; i < len + 1; i++) {
            int cur = nums[i - 1];
            for (int j = 0; j < halfSum + 1; j++) {
                if (j < cur) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - cur];
                }
            }
        }
        return dp[len][halfSum];

//        boolean[] dp = new boolean[sum + 1];
//        dp[0] = true;
//
//        for (int i = 1; i <= len; i++) {
//            for (int j = sum; j >= nums[i - 1]; j--) {
//                dp[j] = dp[j] || dp[j - nums[i - 1]];
//            }
//        }
//        return dp[sum];
    }
}
