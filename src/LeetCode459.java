public class LeetCode459 {
    /*
    a b c a d c a b c a d c
    0 0 0 1 0 0 1 2 3 4 5 6
    //Create one KMP table which will tell us about LPS in the string.
    //Find the length of the pattern (if formed).
    //check whether that pattern length is divisible by the length of the given string.
    //Note:- we need to check the condition of the last index of LPS array. If that is 0 then it means that there is
    //no pattern formed in the string.
     */
    public boolean repeatedSubstringPattern(String s) {
        int[] lps = new int[s.length()]; //longest proper prefix
        for (int i = 1, j = 0; i < s.length();) {
            if (s.charAt(i) == s.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else if (j != 0 && s.charAt(i) != s.charAt(j)) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        System.out.println(lps);
        if (lps[lps.length-1] == 0) return false;
        int patternLength = s.length() - lps[lps.length - 1];
        if (s.length() % patternLength == 0) return true;
        return false;
    }
//    public boolean repeatedSubstringPattern(String s) {
//        // so smart
//        int idx = (s + s).indexOf(s, 1);
//        return  idx < s.length();
//    }
}
