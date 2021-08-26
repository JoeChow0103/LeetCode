import java.util.TreeSet;

public class LeetCode220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> tree = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                // condition 1: abs(i - j) <= k
                tree.remove(Long.valueOf(nums[i - k - 1]));
            }

            long upperBound = (long) nums[i] + t;
            long lowerBound = (long) nums[i] - t;
            Long low = tree.lower(upperBound + 1);

            // condition 2: abs(nums[i] - num[j]) <= t
            if (low != null && low >= lowerBound) {
                return true;
            }

            tree.add(Long.valueOf(nums[i]));
        }
        return false;
    }
}
