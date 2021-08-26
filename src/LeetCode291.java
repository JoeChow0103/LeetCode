import java.util.*;

public class LeetCode291 {
    public boolean wordPatternMatch(String pattern, String s) {
        return dfs(pattern, 0, s, 0, new HashMap<Character, String>(),
                new HashSet<String>());
    }

    private boolean dfs(String pattern, int p_index, String str, int index,
                        HashMap<Character, String> map, HashSet<String> set) {
        int len = str.length();
        if (index == len && p_index == pattern.length()) return true;
        if (index >= len || p_index >= pattern.length()) return false;

        char c = pattern.charAt(p_index);
        if (map.containsKey(c)) { // c is in the map
            int curLen = map.get(c).length();
            if (curLen + index > len) return false;
            String target = str.substring(index, index + curLen);
            return target.equals(map.get(c))
                    ? dfs(pattern, p_index + 1, str, curLen + index, map, set)
                    : false;
        } else { // c is not in the map
            int maxLen = maxLength(pattern, p_index, str, index, map); // PRUNING
            if (maxLen < 1) return false;
            for (int l = 1; l <= maxLen; l++) { // PRUNING
                String curStr = str.substring(index, index + l);
                if (set.contains(curStr)) continue;

                map.put(c, curStr);
                set.add(curStr);
                if (dfs(pattern, p_index + 1, str, index + l, map, set)) return true;

                map.remove(c);
                set.remove(curStr);
            }
        }
        return false;
    }

    // pruning: in pattern, c corresponds string s. However, the maxLength of s can be
    // calculated using method maxLength();
    private int maxLength(String pattern, int p_index, String str, int index,
                          HashMap<Character, String> map) {
        int p_len = pattern.length(), s_len = str.length();
        int leftLen = s_len - index, count = 1;

        char thisChar = pattern.charAt(p_index);
        for (int i = p_index + 1; i < p_len; i++) {
            char c= pattern.charAt(i);
            if (c == thisChar) {
                count++;
            } else {
                leftLen -= map.containsKey(c) ? map.get(c).length() : 1;
            }
        }
        return (int) (leftLen / count);
    }
}
