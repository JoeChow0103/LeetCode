public class LeetCode209 {
    /*
    two pointer, move the end and update current sum from
    start to end, if current sum >= target then move the
    start and delete nums[start] until the sum < target,
    and update the minLen = Math.min(end - start + 1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int start = 0, end = 0, sum = 0; //[start, end)
        int minLen = len + 1;

        while (end < len) {
            sum += nums[end];
            if (sum >= target) {
                while (sum - nums[start] >= target) {
                    sum -= nums[start++];
                }
                minLen = Math.min(minLen, end - start + 1);
            }
            end++;
        }
        return minLen == (len + 1) ? 0 : minLen;
    }
}
