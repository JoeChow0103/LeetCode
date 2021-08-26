import java.util.*;

public class LeetCode322 {
    /**
     * [1,2,5], 11
     * 1 2 5
     * 1,1 1,2 1,5
     * when to exit: sum > amount
     * 11, -1 = 10, -1 =9,.... -1 = 0
     * 11, -2 = 9, -2 = 7, ... = 1, -2 =-1 < 0;
     * 11, -5
     * we dfs, ask the next if can get the amount
     * three direction: the number of direction is the number of different coin we have. This determines the loop.
     * every level, we check if we can go further and then repeat.
     * Every time, we minus the coin and ask if can do the rest. And dfs return how many coins can consist of the remain
     * base case: 1. success: remain is zero, 2.failure: remain is negative.
     * One question after that is, we want to have the min number of coins.
     * Thus, we can use a variable to record. Let's say min, which default is MAX_VALUE;
     * So, everytime we call the dfs method, we get the number os coins: temp.
     * And then we update the min by Math.min(min, temp + 1), because we need to count this movement in.
     * Remember, we still need to divides situations to if the base case successful. If not, don't change min.
     * Pruning: we have lots of duplicates here. EX. 3 = 1 + 1 + 1 = 1 + 2, when it goes to 4, it also contains the case 3
     * Thus, we use a int array to remember the result on the path.
     * @param coins
     * @param amount
     * @return
     */
//    public int coinChange(int[] coins, int amount) {
//        //c.c.
//        if (amount == 0) {
//            return 0;
//        }
//
//        int[] mem = new int[amount + 1];
//        return dfs(coins, amount, mem);
//    }
//
//    private int dfs(int[] coins, int remain, int[] mem) {
//        // base case
//        if (remain == 0) {
//            return 0;
//        }
//
//        if (remain < 0) {
//            return -1;
//        }
//
//        if (mem[remain] != 0) {
//            return mem[remain];
//        }
//
//        int min = Integer.MAX_VALUE;
//
//        for(int coin : coins) {
//            int temp = dfs(coins, remain - coin, mem);
//            if(temp != -1) {
//                min = Math.min(min, temp + 1);
//            }
//        }
//
//        mem[remain] = (min == Integer.MAX_VALUE) ? -1 : min;
//        return mem[remain];
//    }

    public int coinChange(int[] coins, int amount) {
        // c.c.
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount + 1]; // from 0 to amount, need (amount + 1) length
        Arrays.fill(dp, -1); // set default
        dp[0] = 0;
//        dp[1] = 1;
//        coins = [1,2,4]
//        dp[2] = 1; // dp[1] + dp[1], 2
//        dp[3] = 2; // dp[1] + dp[2]; dp[negative]
//        dp[4] = dp[1] + dp[3] = 4, dp[2] + dp[2] = 4
//        dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
//        dp[i] = dp[i - coin] + 1
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) { // EX. [2] 3
                    dp[i] = (dp[i] == -1) ? dp[i - coin] + 1 : Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        LeetCode322 solution = new LeetCode322();
        int[] coins = {1,2,5};
        int amount = 11;
        int res = solution.coinChange(coins, amount);
        System.out.println(res);
    }
}
