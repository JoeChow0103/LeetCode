public class LeetCode91 {
    /*
    (i)
    |
    (i-1) + (i-2)
    |
    if (0) 0, else (i-1); if ([10,26])

    dp[i] = dp[i-1], if one digit, dp[i-1]; += dp[i-2], if two digit; else 0
    init: dp[0] = 1, dp[1] = '0' ? 0 : 1;
     */
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // '0' doesn't have a single digit decode

        for (int i = 2; i < dp.length; i++) {
            // check if successful single digit decode is possible
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i-1];
            }
            // check if successful two digit decode is possible
            int twoDigit = Integer.valueOf(s.substring(i-2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];

//        int oneBack = 0, twoBack = 1;
//        if (s.charAt(0) > '0' && s.charAt(0) <= '9') oneBack = 1;
//        for (int i = 1; i < s.length(); i++) {
//            int sum = 0;
//            char c = s.charAt(i);
//            if (c > '0' && c <= '9') sum += oneBack;
//            char prev = s.charAt(i - 1);
//            int val = (prev - '0') * 10 + (c-'0');
//            if (val >= 10 && val <= 26) sum += twoBack;
//            twoBack = oneBack;
//            oneBack = sum;
//        }
//        return oneBack;
    }

//    public int numDecodings(String s) {
//        return search(s, 0);
//    }
//
//    private int search(String s, int idx) {
//        // base case
//        if (idx == s.length()) return 1;
//
//        int res = 0;
//        for (int l = 1; l <= 2; l++) {
//            if (idx + l > s.length()) {
//                break;
//            }
//            String str = s.substring(idx, idx + l);
//            int num = Integer.valueOf(str);
//            if (1 <= num && num <= 26) {
//                res += search(s, idx + l);
//            }
//
//            if (num == 0) {
//                return 0;
//            }
//        }
//        return res;
//    }
}
