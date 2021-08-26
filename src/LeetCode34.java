public class LeetCode34 {
    public int[] searchRange(int[] nums, int target) {
        int res[] = new int[2];
        res[0] = -1;
        res[1] = -1;

        int len = nums.length;
        int start = 0, end = len - 1;
        while (start <= end) { // nums[start] = target
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start >= len || nums[start] != target) return res;

        res[0] = start;
        end = len - 1;
        while (start <= end) { // nums[end] = target
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        res[1] = end;
        return res;
    }
}
