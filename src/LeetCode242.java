public class LeetCode242 {
    /*
    solution 1: sort
    solution 2: use a 26 size array to store the numbers of char for the first string and the second string.
    Then see if the same.
    Time: O(n), Space: O(26) -> O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i) - 'a';
            freq[pos] += 1;
        }

        for (int j = 0; j < t.length(); j++) {
            int pos = t.charAt(j) - 'a';
            freq[pos] -= 1;
        }

        for (int i : freq) {
            if (i != 0) return false;
        }
        return true;
    }
}
