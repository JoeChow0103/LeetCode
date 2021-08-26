public class LeetCode41 {
    /*
    put the 1 with index 1, put the 2 with index 2, ignore the zero and negatives.
    Check if index + 1 == num, if not return the index + 1;
    another situation, if [1,2,3,4,5,...] then return len + 1;
    Time: O(N), Space: O(1)
    3 4 -1 1
    1 -1 3 4
    min = 1, max = nums.length
    f = nums[i] - 1, hash function for reindex
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                // nums[i] belong min and max, nums[i] is on i
                // swap
                int n = nums[i];
                nums[i] = nums[n - 1];
                nums[n - 1] = n;
            }
        }

        for(int i = 0; i < len; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return len + 1;
    }
}
