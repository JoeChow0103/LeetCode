import java.util.Arrays;

public class LeetCode300 {
    /*
    solution1: recursion: choose or not choose
    if cur < pre, then include and update len; else, not include
    Time: O(2^n), Space: O(1)
    solution2: improve with mem[i][j] means the maxLen from i to j
    Time: O(n^2), Space: O(n^2)
    solution3: dp
    the length is depend on all the element before, so keep dp[i] as the max len
    from index 0 to i
    dp[i] = max(dp[j]) + 1
    result = max(dp[i])
    Time: O(n^2), Space: O(n^2)
    solution4: binary search dp
    dp[i] store the first number initially
    1. if nums[i] is larger then dp[index - 1], then dp[index] = nums[i]
    which means nums[i] is currently in a right position
    2. if nums[i] < dp[index - 1], find which position in dp[] is the right place for nums[i]
    . find it and assign the value, so in dp[], it's always sorted
     */
//    public int lengthOfLIS(int[] nums) {
//        if(nums == null || nums.length == 0){
//            return 0;
//        }
//        Integer[][] mem = new Integer[nums.length][nums.length];
//        return dfs(nums, -1, 0, mem);
//
//    }
//
//    private int dfs(int[] nums, int preIndex, int curIndex, Integer[][] mem){
//        System.out.println("pre:" + preIndex);
//        System.out.println("cur:" + curIndex);
//        if(curIndex == nums.length){
//            return 0;
//        }
//
//        if(mem[preIndex + 1][curIndex] != null){
//            return mem[preIndex + 1][curIndex];
//        }
//
//        //choose
//        int choose = 0;
//        if(preIndex < 0 || nums[preIndex] < nums[curIndex]){
//            choose = 1 + dfs(nums, curIndex, curIndex + 1, mem);
//        }
//
//        //not choose;
//        int notChoose = dfs(nums, preIndex, curIndex + 1, mem);
//        System.out.println("choose:"+ choose);
//        System.out.println("notChoose:" + notChoose);
//        mem[preIndex + 1][curIndex] = Math.max(choose, notChoose);
//        return mem[preIndex + 1][curIndex];
//    }

//    public int lengthOfLIS(int[] nums) { // nlogn
//        int len = nums.length;
//        int[] increaSeq = new int[len];
//
//        int index = 0;
//        increaSeq[0] = nums[index++];
//        for (int i = 1; i < len; i++) {
//            if (nums[i] > increaSeq[index - 1]) {
//                increaSeq[index++] = nums[i];
//            } else {
//                int pos = findPosition(increaSeq, 0, index - 1, nums[i]);
//                increaSeq[pos] = nums[i];
//            }
//        }
//        return index;
//    }
//
//    private int findPosition(int[] nums, int start, int end, int target) {
//        int left = 0, right = end;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] == target) {
//                return mid;
//            } else if (target > nums[mid]) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return left;
//    }

    public int lengthOfLIS(int[] nums) { // n^2
        // dp[i] nums[0,i]'s LIS's length
        // dp[i] = dp[j] + 1, 0 <= j < 1 && nums[i] > nums[j]
        /*
        [i]int
        |
        max
        |
        j, 0 <= j < 1 && nums[i] > nums[j]
        init: dp[i] = 1;
         */
        int len  = nums.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < len+1; i++) max = Math.max(max, dp[i]);
        return max;
    }


    public static void main(String[] args) {
        LeetCode300 solution = new LeetCode300();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7};
        solution.lengthOfLIS(nums);
    }
}
