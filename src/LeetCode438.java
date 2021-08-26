import java.util.*;

public class LeetCode438 {
    /*
    sliding window to check if the char is in the p.
    HashMap
    Time: O(n1 + n2); Space: O(1) since biggest size is 26
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        HashMap<Character, Integer> toFind = new HashMap<>();
        for (char c : p.toCharArray()) toFind.put(c, toFind.getOrDefault(c, 0) + 1);

        HashMap<Character, Integer> hasFound = new HashMap<>();
        int count = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hasFound.put(c, hasFound.getOrDefault(c, 0) + 1);

            if (i >= p.length()) {
                char ch = s.charAt(i - p.length());
                if (hasFound.get(ch) == 1) hasFound.remove(ch);
                else hasFound.put(ch, hasFound.get(ch) - 1);
            }
            if (hasFound.equals(toFind)) res.add(i - p.length() + 1);
        }
        return res;
    }

    // if use array
//    public List<Integer> findAnagrams(String s, String p) {
//        List<Integer> result = new ArrayList<>();
//        // corner case
//        if (s == null || s.length() == 0 || p == null || p.length() > s.length()) {
//            return result;
//        }
//
//        for (int i = 0; i < s.length() - p.length() + 1; i++) {
//            if (checkPattern(s.substring(i, i + p.length()), p)) {
//                result.add(i);
//            }
//        }
//        return result;
//    }
//
//    private boolean checkPattern(String s1, String s2) {
//        if (s1.length() != s2.length()) return false;
//        int[] pattern = new int[26];
//
//        for (char c : s1.toCharArray()) {
//            pattern[c - ‘a’]++;
//        }
//        for (char c : s2.toCharArray()) {
//            pattern[c - ‘a’]--;
//        }
//
//        for (int i : pattern) {
//            if (i != 0) return false;
//        }
//        return true;
//    }


}
