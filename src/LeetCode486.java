public class LeetCode486 {
    /*
      //solution1: use sum to separate first hand and second hand
                                    dp[i,j]: return_type:int
                                       |
                                      Max
                        /                               \
            nums[i]+sum[i+1,j]-dp[i+1,j]    nums[j]+dp[i,j-2]+sum[i,j-1]-dp[i,j-1], 0 <= i < j <= len-1
       dp[i][j] current user [i, j] MAX VAL
       init:dp[i][i] = nums[i];
       // solution2: if don't use sum
       A: get i             get i+2||j
       B:       get i+1 || j
       if A want to get the max, the strategy is to let B get the number and leave the smaller number to A.
       B as first hand is the same strategy.
       so nums[i] + min(dp[i+2,j]"second get i+1", dp[i+1][j-1]"second get j") for the first hand get i
       nums[j] + min(dp[i+1,j-1]"second get i", dp[i+2][j]"second get j-1") for the first hand get j,
       dp[x,y]+dp[x+1,y] = sum, A get x, b get
       dp[x,y]+dp[x,y-1] = sum, A get y, b get
     */
    public boolean PredictTheWinner(int[] nums) {// O(n^2)
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

//        // solution1:
//        // sum[i,j] = prefixSum[j] - prefixSum[i-1]
//        int[] prefixSum = new int[len];
//        for (int i = 0; i < len; i++) {
//            if (i == 0) prefixSum[i] = nums[i];
//            else {
//                prefixSum[i] = prefixSum[i - 1] + nums[i];
//            }
//        }
//
//        for (int i = len - 1; i >= 0; i--) {
//            for (int j = i; j < len; j++) {
//                // consider about the i||j=0
//                dp[i][j] = Math.max(nums[i] + (prefixSum[j] - prefixSum[i]) - dp[i + 1][j], // left
//                        nums[j] + dp[i][j - 2] + (prefixSum[j - 1] - prefixSum[i - 1]) - dp[i][j - 1]); // right
//            }
//        }
//
//        return dp[0][len-1] > prefixSum[len-1] - dp[0][len-1]; // user1 compare user2
        //solution2:
        int sum = 0;
        for (int i = len-1; i >= 0; i--) {
            sum += nums[i];
            for (int j = i; j < len; j++) {
                int a = (i + 2 <= j) ? dp[i+2][j] : 0;
                int b = (i + 1 <= j - 1) ? dp[i+1][j-1] : 0;
                int c = (i <= j - 2) ? dp[i][j - 2] : 0;

                dp[i][j] = Math.max(nums[i] + Math.min(a, b),
                        nums[j] + Math.min(b,c));
            }
        }
        return dp[0][len - 1] >= sum - dp[0][len - 1];
    }
}
