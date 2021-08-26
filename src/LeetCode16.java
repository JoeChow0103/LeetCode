import java.util.Arrays;

public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = nums[0] + nums[1] + nums[2]; // initially, no reason
        for (int i = 0; i < len; i++) {
            int l = i + 1, r = len - 1;
            int first = nums[i];
            while (l < r) {
                int total = nums[l] + nums[r] + first;
                if (total == target) return total;
                if (Math.abs(total - target) < Math.abs(target - sum)) sum = total;
                if (total < target) l++;
                if (total > target) r--;
            }
        }
        return sum;
    }
}
