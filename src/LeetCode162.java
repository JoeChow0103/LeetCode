public class LeetCode162 {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (mid - 1 >= 0 && nums[mid] > nums[mid - 1]) left = mid;
            else right = mid;
        }

        return nums[right] > nums[left] ? right : left;
    }
}
