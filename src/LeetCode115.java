public class LeetCode115 {
    /*
    dfs(i,j) s1[i]==s2[j] match or not match; s1[i] != s2[j] skip
    dp[i,j]: s1[0,i) s2[0,j) => total number
                         dp[i,j], return_type:int
                         /     \
            s1[i] != s2[j]   s1[i] == s2[j]
                   |                   |
              dp[i-1, j]              sum
                                   /       \
                       dp[i-1, j-1](match)  dp[i-1,j](not-match)
     dp[i][j] = dp[i-1,j], s1[i] != s2[j]
                dp[i-1,j] + dp[i-1, j-1] s1[i] == s2[j]
     init: dp[i][0] = 1, s2 is empty
     */
    public int numDistinct(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        int m[][] = new int[lenS + 1][lenT + 1];
        for (int i = 0; i < lenS; i++) {
            m[i][0] = 1;
        }

        for (int i = 0; i < lenS; i++) {
            for (int j = 0; j < lenT; j++) {
                char sc = s.charAt(i), tc = t.charAt(j);
                if (tc != sc) {
                    m[i + 1][j + 1] = m[i][j + 1];
                } else {
                    m[i + 1][j + 1] = m[i][j] + m[i][j + 1];
                }
            }
        }
        return m[lenS][lenT];
    }
}
