public class LeetCode392 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() == 0) return true;

        for (int i = 0, j = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) j++;
            if (j == s.length()) return true;
        }
        return false;
    }
}
