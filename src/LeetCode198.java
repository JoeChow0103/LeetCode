public class LeetCode198 {
    /*
    solution1: recursion
    robFrom(i) = max(robFrom(i+1), robFrom(i+2) + num[i])
    Time: O(2^n), Space: O(1)
    solution2: improve with mem to remove duplicate, LC70
    Time: O(n), Space: O(n)
    solution3: dp
    maintain a dp[i] to store max value can be get from 0 to i
    Time: O(n), Space: O(n)
    solution4: improve with one variable
    Time: O(n), Space: O(1)
     */
//     public int rob(int[] nums) {

//         int[] memo = new int[nums.length];
//         Arrays.fill(memo, -1);

//         return helper(nums, 0, memo);
//     }

//     private int helper(int[] nums, int idx, int[] memo){
//         if(idx >= nums.length) {
//             return 0;
//         }

//         if(memo[idx] != -1){
//             return memo[idx];
//         }

//         // case rob current house
//         int rob = helper(nums, idx + 2, memo) + nums[idx];

//         // case unrob current house
//         int unrob = helper(nums, idx + 1, memo);

//         int max = Math.max(rob, unrob);
//         memo[idx] = max;
//         return max;
//     }

    public int rob(int[] nums) {
//        int len = nums.length;
//        int[] rob = new int[len];
//        rob[0] = nums[0];
//        int[] unrob = new int[len];
//
//        for(int i = 1; i < nums.length; i++){
//            rob[i] = unrob[i - 1] + nums[i];
//            unrob[i] = Math.max(rob[i - 1], unrob[i - 1]);
//        }
//
//        return Math.max(rob[len - 1], unrob[len - 1]);


        int rob = nums[0];
        int unrob = 0;

        for(int i = 1; i < nums.length; i++){
            int curRob = unrob + nums[i];
            int curUnrob = Math.max(unrob, rob);
            rob = curRob;
            unrob= curUnrob;
        }

        return Math.max(rob, unrob);
    }
}
