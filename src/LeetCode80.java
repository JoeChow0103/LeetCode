public class LeetCode80 {
    public int removeDuplicates(int[] nums) {
        int prev = 0, cur = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[prev] != nums[i] || nums[cur] != nums[i]) {
                nums[cur + 1] = nums[i];
                prev = cur;
                cur++;
            }
        }
        return cur + 1;
    }
}
