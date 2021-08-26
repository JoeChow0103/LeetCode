import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeetCode219 {
    /*
    nums = [1,2,3,1], k = 3
    hashset one pass
    Time: O(n)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        // corner case;
        if (nums == null || len < k) {
            return false; // throw exception
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
