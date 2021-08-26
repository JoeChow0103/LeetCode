public class LeetCode410 {
    /*
    dp[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts
    dp[i][j] = min(dp[i][j], max(dp[k][j-1], sum[k+1, i]))
    O(n^2*m)
     */
//    public int splitArray(int[] nums, int m) {
//        int[] prefix = new int[nums.length];
//        int[][] dp = new int[nums.length + 1][m + 1];
//        prefix[0] = nums[0];
//        for(int i = 1; i < nums.length; i++){
//            prefix[i] = prefix[i - 1] + nums[i];
//        }
//        for(int i = 0; i < nums.length; i++){             // up to which index
//            dp[i][0] = Integer.MAX_VALUE;
//            for(int j = 1; j <= m; j++){                  // up to how many parts
//                int min = Integer.MAX_VALUE;
//                if(j == 1) {
//                    dp[i][j] = prefix[i];
//                    continue;
//                }
//                if(j > (i + 1)){
//                    dp[i][j] = Integer.MAX_VALUE;
//                    continue;
//                }
//                for(int k = 0; k < i; k++){
//                    min = Math.min(min, Math.max(dp[k][j - 1], prefix[i] - prefix[k]));
//                }
//                dp[i][j] = min;
//            }
//        }
//        return dp[nums.length - 1][m];
//    }

    private boolean valid(int[] nums, int m, long sumUpperBond, long[] minSum) {
        int count = 1;
        long sum = 0;
        for (int n : nums) {
            sum += (long) n;
            if (sum > sumUpperBond) {
                sum = (long) n;
                count++;
                if (count > m) {
                    return false;
                }
            }
            minSum[0] = Math.max(minSum[0], sum);
        }
        return true;
    }

    private int binarySearch(int[] nums, int m, long min, long max) {
        long start = min, end = max;
        while (start < end) {
            long mid = start + (end - start) / 2;
            long[] minSum = new long[1];
            minSum[0] = min;
            if (valid(nums, m, mid, minSum)) {
                end = minSum[0];
            } else {
                start = mid + 1;
            }
        }
        return (int) start;
    }

    public int splitArray(int[] nums, int m) {
        long sum = 0, min = Integer.MAX_VALUE;
        for (int n : nums) {
            sum += (long) n;
            min = Math.max(min, (long) n);
        }

        if (m == 1) return (int) sum;

        return binarySearch(nums, m, min, sum);
    }
}
