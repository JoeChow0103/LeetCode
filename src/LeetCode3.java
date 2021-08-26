import java.util.HashMap;

public class LeetCode3 {
    /*
    slow/fast pointer.
    slow != fast, fast++
    slow = fast, slow++, fast++
    len = fast - slow + 1,
    res = max(res, fast - slow + 1)
     */
    public int lengthOfLongestSubstring(String s) {
        // c.c.
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            char cur = s.charAt(j);
            if (map.containsKey(cur)) {
                i = Math.max(i, map.get(cur) + 1);
            }
            map.put(cur, j);
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
