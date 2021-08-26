public class LeetCode5 {
    /*
    each number, left right forward to check if left == right.
    Two case: ada, adda;
    Time: O(n^2), Space: O(1)
     */
    public String longestPalindrome(String s) {
        // c.c.
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            int l = i;
            int r = i;
            // ‘baab’ version
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                i++;
                r++;
            }
            // ‘bab’ version
            while (l >= 1 && r < s.length() - 1 && s.charAt(r + 1) == s.charAt(l - 1)) {
                l--;
                r++;
            }
            if (r - l > right - left) {
                left = l;
                right = r;
            }
        }
        return s.substring(left, right + 1);
    }

}
