public class LeetCode97 {
    /*
                                        dp[i,j],return_type:boolean
                            /              |                |               \
                iff s1[i] == s3[k]  iff s2[j] == s3[k]   iff no match    iff s1[i] == s2[j] == s3[k]
                |                           |               |               |
            dp[i-1,j]               dp[i,j-1]             false            ||
                                                                    dp[i-1,j] || dp[i,j-1]

    boolean dp[i][j]
    dp[i,j] is s1[0,i), s2[0,j) true/false match s3
    "if need empty, then [); otherwise, then []"
    dp[i][j] = max(dp[i-1][j], // s1[i] == s3[k]
                   dp[i][j-1], // s2[j] == s3[k]
                   false, // no match
                   dp[i-1][j] || dp[i][j-1]) // s1[i] == s2[j] == s3[k]
    dp[0][0] = true;
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 + len2 != s3.length()) return false;
        boolean m[][] = new boolean[len1 + 1][len2 + 1];
        m[0][0] = true;
        for (int i = 0; i < len1; i++) {
            m[i+1][0] = m[i][0] && (s1.charAt(i) == s3.charAt(i));
        }
        for (int i = 0; i < len2; i++) {
            m[0][i+1] = m[0][i] && (s2.charAt(i) == s3.charAt(i));
        }
        for (int i = 0; i < len1; i++) { // i is the index of string, not dp
            for (int j = 0; j < len2; j++) { // j is the index of string , not dp
                int k = i + j + 1;
                // int dpIdx1 = i + 1, dpIdx2 = j + 1;
                if (s1.charAt(i) == s3.charAt(k)) m[i + 1][j + 1] = m[i][j + 1];
                if (s2.charAt(j) == s3.charAt(k)) {
                    m[i + 1][j + 1] = m[i + 1][j + 1] || m[i + 1][j];
                }
            }
        }
        return m[len1][len2];
    }
}
