public class LeetCode238 {
    /*
    brute force: pre product all the number then divide each number to get the result;
    O(N) however, what about has 0 on it.
    Pre-fix product, use an array to get the pre-fix product from the left to right of with 1 at the first,
    this will product skip the number. Then use another array to do it from right to left.
    Then product each other. Time: O(N), Space: O(N)
    reuse the array 1 as a result. Space: O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int res[] = new int[len];
        for (int i = 0; i < len; i++) res[i] = 1;
        for (int i = 0, temp = 1; i < len; i++) {
            res[i] *= temp;
            temp *= nums[i];
        }

        for (int i = len - 1, temp = 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }

        return res;
    }
}
