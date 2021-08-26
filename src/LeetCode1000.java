import java.util.*;

public class LeetCode1000 {
    /*
    stones = [3,2,4,1,5,6], k = 3
    round 1:
    3 + 2 + 4 = 9 cost
        2 + 4 + 1 = 7 [7,1,5,6]
            4 + 1 + 5 = 10
                1 + 5 + 6 = 12
    round 2:
    7 + 1 + 5 = 14
        1 + 5 + 6 = 12 [12, 14]

    round 3:
    12 + 14 = 26 wrong
    total cost = 9 + 7 + 12 + 26 = 54

    stones = [3,2,4,1,5], k = 3
    round 1:
    3 + 2 + 4 = 9
        2 + 4 + 1 = 7 [7,1,5,6]
            4 + 1 + 5 = 10
    round 2:
    7 + 1 + 5 = 14
    total cost = 7 + 14 = 21

     */
    public int mergeStones(int[] stones, int K) {
        int len = stones.length;
        if (len == 1) {
            return 0;
        } else if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[][][] dp = new int[len][len][K + 1];
        int[] preSum = new int[len + 1]; // preSum[i] —— preSum of stones[0 - i]
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        // initialization
        // 不用Integer.MAX_VALUE,因为Integer.MAX_VALUE + 正数 会溢出变为负数
        // int MAX_VALUE = Integer.MAX_VALUE / 2;
        // for (int i = 0; i < len; i++) {
        //     for (int j = i; j < len; j++) {
        //         Arrays.fill(dp[i][j], 2, K + 1, MAX_VALUE);
        //     }
        //     // dp[i][i][1] = 0; 可以省略，因为默认的int数组申请好空间之后，的默认值就是0
        // }

        for (int size = 2; size <= len; size++) { // size, 枚举区间长度
            for (int i = 0; i + size - 1 < len; i++) { // i, 枚举区间起点
                int j = i + size - 1;
                for (int m = 2; m <= K; m++) { // m, 枚举堆数
                    dp[i][j][m] = Integer.MAX_VALUE;
                    for (int cut = i; cut < j; cut += K - 1) {  // cut, 枚举分界点
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][cut][1] + dp[cut + 1][j][m - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + sum(preSum, i, j);
            }
        }
        return dp[0][len - 1][1];
    }

    private int sum(int[] preSum, int i, int j) {
        return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
    }
}
