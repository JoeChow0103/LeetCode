import java.util.HashMap;
import java.util.HashSet;

public class LeetCode1 {
    /*
    one pass hashmap
     */
    public int[] twoSum(int[] nums, int target) {
        // c.c.
        if (nums == null || nums.length == 0) return new int[1];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remaining = target - nums[i];
            if (map.containsKey(remaining)) {
                return new int[] {map.get(remaining), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
