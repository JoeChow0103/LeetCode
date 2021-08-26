public class LeetCode87 {

    // S1: DP
    // time = O(n^4), space = O(n^3)
    public boolean isScrambleDP(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2) return false;

        boolean[][][] dp = new boolean[len1][len1][len1 + 1];
        // 初始化单个字符
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len1; j++) {
                dp[i][j][1] = ch1[i] == ch2[j];
            }
        }

        // 枚举区间长度 2 ~ len1
        for (int l = 2; l <= len1; l++) {
            // 枚举S中的起点位置
            for (int i = 0; i <= len1 - l; i++) {
                // 枚举T中的起点位置
                for (int j = 0; j <= len1 - l; j++) {
                    // 枚举划分的位置
                    for (int k = 1; k <= l - 1; k++) {
                        // case 1: S1 --> T1, S2 --> T2
                        if (dp[i][j][k] && dp[i + k][j + k][l - k]) {
                            dp[i][j][l] = true;
                            break;
                        }
                        // case 2: S1 --> T2, S2 --> T1
                        // S1起点i,T2起点 = j + l - k，S2起点i + k，T1起点 = j
                        if (dp[i][j + l - k][k] && dp[i + k][j][l - k]) {
                            dp[i][j][l] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][len1];
    }
    /*
    branches: for each element can be split,
    and each branch is to compare the s1 and s2's substrings left to right,
    and right to left
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (!isSame(s1, s2)) return false;
        return dfs(s1, s2);
    }

    public boolean dfs(String s1, String s2) {
        if (s1.equals(s2)) return true;

        if (s1.length() == 2 && s2.length() == 2 && s1.charAt(1) == s2.charAt(0)
        && s1.charAt(0) == s2.charAt(1)) return true;

        for (int i = 0; i < s1.length() - 1; i++) {
            String s11= s1.substring(0, i + 1);
            String s12 = s1.substring(i + 1, s1.length());
            String s21 = s2.substring(0, i + 1);
            String s22 = s2.substring(i + 1, s2.length());

            if (isSame(s11, s21) && isSame(s12, s22) && dfs(s11, s21) && dfs(s12, s22)) {
                return true;
            }

            s21 = s2.substring(s2.length() - 1 - i, s2.length());
            s22 = s2.substring(0, s2.length() - 1 - i);

            if (isSame(s11, s21) && isSame(s12, s22) && dfs(s11, s21) && dfs(s12, s22)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSame(String s1, String s2) {
        int count[] = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            int index1 = s1.charAt(i) - 'a';
            count[index1]++;

            int index2 = s2.charAt(i) - 'a';
            count[index2]--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }

        return true;
    }
}
