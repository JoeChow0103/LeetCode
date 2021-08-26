public class LeetCode154 {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[start]) {
                start = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                if (nums[start] == nums[mid] && nums[start] == nums[start + 1]) {
                    start++;
                } else if (nums[end] == nums[mid] && nums[end] == nums[end - 1]) {
                    end--;
                }
            }
        }
        return nums[start] > nums[end] ? nums[end] : nums[0];
    }
}
