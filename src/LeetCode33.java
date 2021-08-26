public class LeetCode33 {
    /*
    Time: O(logn), Space: O(1)
     */
    public int search(int[] nums, int target){
        // cc
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 先要check mid，如果不check那么就会被忽略
            if (nums[mid] == target) return mid;
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            if (nums[left] < nums[mid]) { // both left and mid in 1 or 2
                if(target > nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // left in 1, mid in 2
                if (target > nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } // 在以上比较中缺少了mid，left，right和target相等的情况，所以要单独拿出来比较
        }
        return -1;
    }

}
