import java.util.HashMap;
import java.util.Map;

public class LeetCode1155 {
    /*
    dp[d][f] = dp[d-1][f-1] + dp[d-1][f-2]+ ...+ dp[d-1][f-d-1]
     */
    final Map<String, Integer> memo = new HashMap<>();
    final int MOD = 1000000007;

    public int numRollsToTarget(int d, int f, int target) {
        if (target > d * f || target < d) return 0;
        if (d == 1) return (target <= f) ? 1 : 0;

        final String key = "" + d + f + target;

        if (! memo.containsKey(key)) {
            int currentSum = 0;

            for (int i = f; i > 0; i--) {
                currentSum += numRollsToTarget(d - 1, f, target - i);
                currentSum %= MOD;
            }
            memo.put(key, currentSum);
        }
        return memo.get(key);
    }
}
