public class LeetCode132 {
    /*
    backward:
    if palindrome, cut or not; if not, skip.
    dp[i] is min number of cut str of [i, len) as palindrome successfully.
    transformation graph:
                        dp[i]: return_type: int
                          |
                         min
                          |
                       dp[j+1] + 1, s[i,j] is palindrome && i <= j < len

    int dfs(i) {
        for (j = i; j < len; j++) {
            str = s.substring(i, j + 1); // [i, j]
            if (isPal(str)) {
                dfs(j);
            }
        }
    }
    int[] dp is dp[i] : s[i,len) => min #(p.substring)
    dp[i] = min(dp[j+1]) + 1, i <= j < len && s[i,j] is p
    init: dp[len]: s[len,len], return: dp[0]
     */
//    public int minCut(String s) { // O(n^3)
//        int len = s.length();
//        int[] dp = new int[len + 1];
//        dp[len] = 0;
//        for (int i = len - 1; i >= 0; i--) { // O(n)
//            // improve to n^2,
//            dp[i] = len - i; // say every character cut
//            for (int j = i; j < len; j++) { // O(n)
//                String str = s.substring(i, j + 1); // s[i, j], O(n)
//                if (isPalindrom(str)) {
//                    dp[i] = Math.min(dp[j + 1] + 1, dp[i]);
//                }
//            }
//        }
//        return dp[0] - 1; //
//    }
//
//    private boolean isPalindrom(String str) {
//        int left = 0, right = str.length() - 1;
//        while (left <= right) {
//            if (str.charAt(left) != str.charAt(right)) return false;
//            left++;
//            right--;
//        }
//        return true;
//    }
    public int minCut(String s) { // O(n^2)
        /*
        isP[i][j] = isP[i+1][j-1] && s[i] == s[j]
         */
        int len = s.length();
        boolean isPalindrome[][] = new boolean[len][len];
        int rec[] = new int[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            rec[i] = len - i;
            for (int j = i; j < len; j++) {
                if (i == j || (s.charAt(i) == s.charAt(j)
                && (j == i + 1 || isPalindrome[i+1][j-1]))) { // i==j and j==i+1
                    rec[i] = Math.min(rec[i], rec[j + 1] + 1);
                    isPalindrome[i][j] = true;
                }
            }
        }
        return rec[0] - 1;
    }
}
