public class LeetCode53 {
    /*
    solution1: brute force, For every start and end, calculate the sum, update the max
    Time: O(n^2), Space: O(1)
    solution2: improve the sum method, using pre-fixed sum
    Time: O(n), Space: O(n)
    solution3: improve the sum method as dp[i],
    if positive contribute, the sum added by the number. if negative contribute reset the sum
    solution4: improve the dp from one dimension to one variable
    Time: O(n), Space: O(1)
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] result = new int[nums.length];
        result[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i] + (result[i - 1] < 0 ? 0 : result[i - 1]);
            max = Math.max(max, result[i]);
        }

        return max;

//        int max = nums[0];
//        int sum = nums[0];
//
//        for(int i = 1; i < nums.length; i++){
//            sum = sum > 0 ? sum + nums[i] : nums[i];
//            max = Math.max(max, sum);
//        }
//
//        return max;
    }
}
