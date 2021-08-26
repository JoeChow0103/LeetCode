public class LeetCode312 {
    /*
             dp[i,j], return_type = int
                |
               Max
                |
             dp[i, k-1] + dp[k+1, j] + nums[k-1]*num[k]*nums[k+1], first burst the k
             // above have the issue when dealing with the dp[i, k-1],dp[k+1, j]
             // need extra parameter
             dp[i, k-1] + dp[k+1, j] + nums[i+1]*nums[k]*nums[j-1], last burst the k
    before I burst k in [i, j], in a big range[x,y], burst the i-1, j+1, [x,i-2],[j+2,y].
    so 1.burst [x, i-2],
        2.then[i,k-1], then [k+1,j],
        2.then k,
       1.then i-1
    dp[i][j] = max(dp[i][k-1] + dp[k+1][j] + nums[i+1]*nums[k]*nums[j-1]), i<= k <= j
    init: dp[i][i] = nums[i]
    from i to j, find a k, burst the k at last not at first.
    Because won't change the positions of neighbors.
    followUp: burst k at first and last not the same. Yes, it's not.
    But I need to try all combination of the each nums then I definitely
    meet the combination of the first burst k. From the result, they are the same.
    (i,) <- max <- (i,k-1)+(k+1,j)+[i-1][k][j+1]
    dp[i][j] = max(dp[i][k-1] + dp[k+1][j] + nums[i+1]*nums[k]*nums[j-1]), i<= k <= j
    i depend on k+1, k+1 is bigger than i
     */
    public int maxCoins(int[] nums) {
        //cc
        int len = nums.length;
        int[][] m = new int[len][len];
        // if the cost has negative, then initial m = Integer.MIN_VALUE

        for (int start = len - 1; start >= 0; start--) { // i depend on k+1, backward
            for (int end = start; end < len; end++) {
                int max = 0;
                for (int i = start; i <= end; i++) {
                    int val = 0;
                    val += (i == start ? 0 : m[start][i - 1]);
                    val += (i == end ? 0 : m[i + 1][end]);

                    // Here is nums[start-1/end+1], not nums[i-1/i+1]
                    // because this split is executed in the last step
                    val += (start == 0 ? 1 : nums[start-1]) * nums[i] *
                            (end == len - 1 ? 1 : nums[end + 1]);
                    max = Math.max(max, val);
                }
                m[start][end] = max;
            }
        }
        return m[0][len - 1];
    }
}
