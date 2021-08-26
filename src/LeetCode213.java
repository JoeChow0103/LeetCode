public class LeetCode213 {
    /*
    (i)
    |
    max
    |
    (i-1)  (i-2)+nums[i]
    /  \
    unrob rob
    init: unRob = 0, Rob = nums[0]
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int len = nums.length;
        return Math.max(rob_simple(nums, 0, len - 2), rob_simple(nums, 1, len - 1));
    }

    private int rob_simple(int[] nums, int start, int end) {
        // nums: 7 4 1 9 3 8 6 5 like cycle
        // case1: 7 4 1 9 3 8 6, case2: 4 1 9 3 8 6 5
        int unRob = 0, rob = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int curRob = unRob + nums[i];
            int curUnRob = Math.max(unRob, rob);
            rob = curRob;
            unRob = curUnRob;
        }
        return Math.max(rob, unRob);
    }
}
