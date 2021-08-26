public class LeetCode45 {
    /*
    clarify if can jump to the end
    solution1: dp, rightmost to leftmost
    dp[i] how many minimum times we can jump from i to target
    minStep = Math.min(dp[i+j], minStep);
    Time: O(n^2), Space(n)
    solution2: greedy
    colored
    2|4 5|6 4 0 0 5|0 0 0 2 1
    when hit | then time++, i > pre to mark the |; cur
    [,preRange] is the last position can jump,
    (preRange, curRange] is the current position can jump
    maintain minStep to update, use curRange to justify whether can hit the target
    Time: O(n), Space(1)
     */
//    public int canJump(int[] nums){
//        if(nums == null || nums.length < 2) return 0;
//        int len = nums.length;
//        int[] dp = new int[len];
//        dp[len-1] = 0;
//        for(int i = len-2; i >= 0; i--){
//            int minStep = Integer.MAX_VALUE;
//            for(int j = 1; j <= nums[i]; j++){
//                if(i + j < len){
//                    minStep = Math.min(dp[i+j], minStep);
//                }
//            }
//            if (minStep == Integer.MAX_VALUE) dp[i] = Integer.MAX_VALUE;
//            else dp[i] = minStep+1;
//        }
//        return dp[0];
//    }

    public int canJump(int[] nums){
        if(nums == null || nums.length < 2) return 0;
        int minStep = 0;
        int prevRange = 0;
        int curRange = 0;
        for(int i = 0; i < nums.length; i++){
            if(curRange >= nums.length - 1) return minStep + 1;
            if(i > prevRange){
                prevRange = curRange;
                minStep++;
            }
            curRange = Math.max(curRange, i + nums[i]);
        }
        return minStep;
    }
}
