public class LeetCode159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        /*
        use idx1 and idx2 to store two distinct char
        and start to store the first time find the distinct char,
        while the two distinct char repeatedly occur, update the idx1 idx2,
        and also maintain a max to store the most length from the start
        to current char. Until the char become different, we don't change start
         */
        int len = s.length();
        char c1 = '\0', c2 = '\0';
        int idx1 = -1, idx2 = -1, start = 0, max = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == c1) {
                idx1 = i;
            } else if (c == c2) {
                idx2 = i;
            } else {
                if (idx1 < idx2) {
                    start = idx1 + 1;
                    c1 = c;
                    idx1 = i;
                } else {
                    start = idx2 + 1;
                    c2 = c;
                    idx2 = i;
                }
            }
            max = Math.max(i - start + 1, max);
        }
        return max;
    }
}
