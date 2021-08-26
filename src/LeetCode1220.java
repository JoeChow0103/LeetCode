import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCode1220 {
    /*
        aCountNew = eCount + iCount + uCount
        eCountNew = aCount + iCount
        iCountNew = eCount + oCount
        oCountNew = iCount
        uCountNew = iCount + oCount
     */

    public int countVowelPermutation(int n) {
        if (n == 1) return 5;

        int mod = (int) (1e9 + 7);

        long[][] dp = new long[n+1][5];
        for (int i = 0; i < 5; i++) {
            dp[0][i] = 0;
            dp[1][i] = 1;
        }

        int[][] graph = new int[][] {
                {1}, {0, 2}, {0, 1, 3, 4}, {2, 4}, {0}
        };

        for (int i = 1; i < n; i++) {
            for (int node = 0; node < 5; node++) {
                for (int neighbor : graph[node]) {
                    dp[i + 1][node] += dp[i][neighbor] % mod;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[n][i]) % mod;
        }

        return (int) ans;
    }

}
