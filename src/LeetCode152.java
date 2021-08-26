import java.util.*;

public class LeetCode152 {
    /*
    sol1: brute force
    sol2: dp, consider the min and max, for negative * negative is positive, the min could be max,
    and max could be min as well. So keep min and max
    Time: O(n), Space: O(n)
    Improve: Space: O(1), update the max and min with traverse
    */
    /**
     * input: [2, 3, -2, 4, -1]
     * min:     2, 6, -2, 4, 48
     * max:     2, 6, -12, -48, -4
     */
    public int maxProduct(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) return 0;
        int preMax = nums[0];
        int preMin = nums[0];
        int max = nums[0];
        int curMax, curMin;
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            curMax = Math.max(Math.max(preMax * temp, preMin * temp), temp);
            curMin = Math.min(Math.min(preMax * temp, preMin * temp), temp);
            max = Math.max(curMax, max);
            preMax = curMax;
            preMin = curMin;
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode152 testCase = new LeetCode152();
        int[] nums = new int[] {2, 3, -2, 4};
        System.out.println(testCase.maxProduct(nums));
    }
}
