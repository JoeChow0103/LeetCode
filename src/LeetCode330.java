public class LeetCode330 {
    /*
    1, 2, 5
    miss = 4, nums[2] = 5, 5 > 4, then
    miss -> 8, cover [1, 8),
    1, 2, 4, 5
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0, i = 0;
        long miss = 1;
        while (miss <= n) {
            // if the nums[i] still covered be [1, miss)
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else { // if the nums[i] cannot be covered
                miss += miss;
                patches++;
            }
        }
        return patches;
    }
}
