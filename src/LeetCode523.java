import java.util.*;

public class LeetCode523 {
    /*
    prefix sum, sum % k
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = k == 0 ? sum : sum % k;
            if (!map.containsKey(target)) {
                map.put(target, i);
            }
            else if (i - map.get(target) > 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode523 solution = new LeetCode523();
        int[] nums = new int[]{23, 2, 4, 6, 7};
        int k = 6;
        solution.checkSubarraySum(nums, k);
    }
}
