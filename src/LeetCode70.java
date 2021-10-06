public class LeetCode70 {
//    f(1) + f(2) = f(3): 1 + 2 = 3
//    f(1) + f(3) = f(4): 1 + 3 = 4
//    f(2) + f(3) = f(5): 2 + 3 = 5
//
//    s1: brute Time: O(2^n), space: O(n)
//            n = 3                      remain;
//    level 0                                (0,5)
//    level 1                      (1,5)                        (2,5)
//    level 2               (2,5)           (3,5)  |       (3,5)      (4,5)
//    level 3           (3,5)   (4,5) | (4,5)  (5,5) | (4,5) (5,5)| (5,5) (6,5)
//    level 4     (4,5) (5,5)|(5,5)   |(5,5)(6,5)   (5,5)  (6,5)
//    level 5 (5,5) (6,5)

//    public int climbStairs(int n) {
//        return climb(0, n);
//    }
//
//    private int climb(int i, int n) {
//        if( i > n) return 0;
//        if (i == n) {
//            return 1;
//        }
//        return climb(i + 1, n) + climb(i + 2, n);
//    }

//    s2： Recursion: 去除重复的步骤，存起来 Time: O(n), space: O(n)

//    public int climbStairs(int n) {
//        int memo[] = new int[n + 1];
//        return climb(0, n, memo);
//    }
//
//    private int climb(int i, int n, int memo[]) {
//        if (i > n) return 0;
//        if (i == n ) return 1;
//        if (memo[i] > 0) return memo[i];
//        memo[i] = climb(i + 1, n, memo) + climb(i + 2, n, memo);
//        return memo[i];
//    }



//    s3: dp: dp[i] = dp[i - 1] + dp[i - 2] 从下向上做 Time: O(n), space: O(n)
//    base case: dp[1] = 1, dp[2] = 2

//    public int climbStairs(int n) {
//        if (n == 1) return 1;
//        int[] dp = new int[n+1]; // 其实多一些size也行
//        dp[1] = 1;
//        dp[2] = 2;
//        for (int i = 3; i < n + 1; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
//    }

//    s4: Fibonacci Time: O(n), space: O(1)

    public int climbStairs(int n) {
        if ( n <= 2) return n;
        int prePre = 1;
        int pre = 1;
        int cur = 2;
        for (int i = 2; i <= n; i++) {
            cur = prePre + pre;
            prePre = pre;
            pre = cur;
        }
        return cur;
    }

//    s5: Fibonacci formula

}
