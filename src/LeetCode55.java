public class LeetCode55 {
    /*
    solution1: recursion
    recursion tree: jump branches, when move to the end or after then canJump
    worst case: see from the tree and each can get end
    Time: O(2^n), Space: O(n)
    solution2: dp
    seeing from the rightmost to left, update dp from the rightmost to leftmost.
    left is depend on right
    If dp[i] is true, then check i + 1 to i + nums[i] is true(good place);
    otherwise false. Time: O(n^2), Space: O(n), worst case: each can jump
    solution3: greedy
    currPos + nums[currPos] >= leftmostGoodIdx, can reach a good position.
    Each step want to reach the rightmost position. nums[i] + i
    maxRange = max(nums[i] + i, maxRange), cannot reach maxRange < i;
    can reach maxRange >= i, maxRange >= len -1
    Time: O(n), Space: O(1)
     */
//    public boolean canJump(int[] nums) {
//        return canJump(nums, 0);
//    }
//
//    private boolean canJump(int[] nums, int index) {
//        if (index >= nums.length - 1) return true;
//
//        int jump = nums[index];
//        for (int i = 1; i <= jump; i++) {
//            if (canJump(nums, index + i)) return true;
//        }
//        return false;
//    }

//    public boolean canJump(int[] nums) {
//        if (nums == null || nums.length < 2) return false;
//        int len = nums.length;
//        boolean[] dp = new boolean[len];
//        dp[len - 1] = true;
//
//        for (int i = len - 2; i >= 0; i--) {
//            for (int j = 1; j <= nums[i]; j++) {
//                if (dp[i+j] == true) {
//                    dp[i] = true;
//                    break; // when check true, no need to check next j
//                } else {
//                    return false;
//                }
//            }
//        }
//        return dp[0];
//    }
    public boolean canJump(int[] nums){
        if(nums == null || nums.length == 0) return true;
        int maxRange = nums[0];
        for(int i = 0; i < nums.length; i++){
            if(maxRange < i) return false;
            maxRange = Math.max(maxRange, nums[i] + i);
            if(maxRange >= nums.length - 1) return true;
        }
        return false;
    }
}
