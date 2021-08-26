import java.util.HashMap;

public class LeetCode1297 {
    /*
    s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
    result = 2
    fixed size: aab, aba, bab, abc, bca, caa, aab => aab, twice
    maxSize is useless
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;

        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String str = s.substring(i, i + minSize);
            if (isValid(str, maxLetters)) {
                map.put(str, map.getOrDefault(str, 0) + 1);
                res = Math.max(map.get(str), res);
            }
        }
        return res;
    }

    private boolean isValid(String str, int maxLetters) {
        int[] freq = new int[26];
        int distinct = 0;
        for (char c : str.toCharArray()) {
            if (freq[c - 'a']++ == 0) distinct++;
        }
        return distinct <= maxLetters;
    }


}
