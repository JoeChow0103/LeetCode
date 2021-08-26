public class LeetCode72 {
    /*
    0. clarify if all the operation is O(1)
    1. Definition dp[i][j] the minimum steps to convert s1.substring [0, i) i letters, to s2.substring [0, j) j letters
    2. Base Case / Start dp[i][0], dp[0][j]
    3. dp[i][j] = min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])+1
        dp[i][j] = dp[i-1][j-1], if s1[i] == s2[j]
                   1 + min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1])
    4. termination: dp[s1.length][s2.length]

            S2   a   b   c   d   e   f
            0    1   2   3   4   5   6
    S1  0   0    1   2   3   4   5   6
    a   1   1    0   1   2   3   4   5
    b   2   2    1   0   1   2   3   4
    e   3   3    2   1   1   2   2   3
    f   4   4    3   2   2   2   3   2
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] m = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) m[i][0] = i;
        for (int i = 0; i <= len2; i++) m[0][i] = i;

        for (int i = 1; i <= len1; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    m[i][j] = m[i - 1][j - 1];
                } else {
                    // insert c1 into word2; delete c1 from word1
                    int insert = m[i - 1][j] + 1;
                    // delete c2 from word2; insert c2 into word2
                    int delete = m[i][j - 1] + 1;
                    // replace c1 with c2
                    int replace = m[i - 1][j - 1] + 1;

                    m[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return m[len1][len2];
    }
}
