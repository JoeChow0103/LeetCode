import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LeetCode15 {
    /*
        when traverse a number, then find the two number from its left can sum as -number.
        deduplicate[-1, -1, -1, 2, 2]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // deduplicate
            int target = -nums[i];
            int l = i + 1, r = len - 1;
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[l]);
                    one.add(nums[r]);
                    res.add(one);
                    l++;
                    r--;
                    // deduplicate[-1,-1,-1,2]
                    while (l < r && nums[l] == nums[l - 1]) l++;
                    while (l < r && nums[r] == nums[r + 1]) r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    r--;
                }

            }
        }
        return res;
    }
}
