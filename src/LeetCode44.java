public class LeetCode44 {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int match = 0; // first place s can match *
        int start = -1;
        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {// not a*
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') { // a*
                start = j;
                match = i;
                j++;
            } else if (start != -1) {
                j = start + 1;
                match++;
                i = match;
            } else return false;
        }
        while (j < p.length() && p.charAt(j) == '*') j++;
        return j == p.length();
    }
    /*
    a d c a b
            i
    * a * b
          j

    match = 1;
    start = 2;
     */
}
