import java.util.Arrays;

public class LeetCode259 {
    /*
    if nums[start] + nums[end] < tar, tar = target - nums[i].
    it means from start to end, there are end - start numbers of
    triplet satisfy the condition. There may be other triplet (
    not start, end, i) satisfy the condition.
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int start = i + 1, end = len - 1;
            int tar = target - nums[i];
            while (start < end) {
                if (nums[start] + nums[end] < tar) {
                    count += end - start;
                    start++;
                } else {
                    end--;
                }
            }
        }
        return count;
    }
}
