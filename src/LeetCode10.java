public class LeetCode10 {
    /*
    a*, branches: '', 'a', 'aa','aaa',...
    worst case:
    s = "aaaaaaa...b" - m
    p = "a*a*a*a*..." - n
    m branches tree, n/2 height, O(m^n)
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int lenS = s.length(), lenP = p.length();
        if (lenP == 0) return lenS == 0;
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];

        dp[lenS][lenP] = true;

        int k = lenP - 2;
        while (k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -=2;
        }

        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }

                if (j + 1 >= lenP || p.charAt(j + 1) != '*') { // p[j+1] != '*'
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else { // p[j+1] == '*'
                    int idx = i - 1;
                    dp[i][j] = false;
                    while (idx < lenS && (idx < i || p.charAt(j) == '.'
                            || p.charAt(j) == s.charAt(idx))) {
                        if (dp[idx + 1][j + 2]) { // start from dp[i][j+2]
                            dp[i][j] = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }
        return dp[0][0];
    }




//    public boolean isMatch(String s, String p) {
//        if (s == null || p == null) return false;
//        int lenS = s.length(), lenP = p.length();
//        Boolean mem[][] = new Boolean[lenS + 1][lenP + 1];
//        return helper(s, 0, p, 0, mem);
//    }

//    private boolean helper(String s, int idxS, String p, int idxP) {
//        int lenP = p.length(), lenS = s.length();
//
//        if (idxP == lenP) {
//            return idxS == lenS;
//        } else if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // not a* type
//            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {// isMatched?
//                return helper(s, idxS + 1, p, idxP + 1);
//            } else {
//                return false;
//            }
//        } else { // a* structure, a* => null, a, aa, aaa,...
//            int i = idxS - 1; // next round, can touch idxS and idxP + 2
//            while (i < lenS
//                    && (i < idxS || p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(i))) {
//                if (helper(s, i + 1, p, idxP + 2)) { // s jump to next a, p jump after a*
//                    return true;
//                }
//                i++;
//            }
//            return false;
//        }
//    }

//    private boolean helper(String s, int idxS, String p, int idxP, Boolean[][] mem) {
//        int lenS = s.length(), lenP = p.length();
//
//        if (mem[lenS][lenP] != null) {
//            return mem[lenS][lenP];
//        }
//
//        if (idxP == lenP) {
//            return idxS == lenS;
//        } else if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') {// not a* type
//            if (idxS < lenS
//                    && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {// isMatched?
//                mem[idxS][idxP] = helper(s, idxS + 1, p, idxP + 1, mem);
//            } else {
//                mem[idxS][lenP] = false;
//                return false;
//            }
//        } else { // a* structure, a* => null, a, aa, aaa,...
//            int i = idxS - 1;
//            while (i < lenS
//                    && (i < idxS || p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(i))) {
//                if (helper(s, i + 1, p, idxP + 2, mem)) {// s jump to next a, p jump after a*
//                    mem[idxS][idxP] = true;
//                    return true;
//                }
//                i++;
//            }
//            mem[idxS][idxP] = false;
//        }
//        return mem[idxS][idxP];
//    }
}
