import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode340 {
    /*
    use a hashmap to store the <char, count>, if keySet.size is
    less and equal to k, then put the char in map. Otherwise,
    remove the leftmost char of s from map until the keySet.size is
    less and equal to k.
    "loveleetcode", k = 4
    map:
    {l:0, o:1, v:2, e:3}, i = 3
    {o:1, v:2, e:3, l:4}, i = 4
    {o:1, v:2, l:4, e:5}, i = 5
    {o:1, v:2, l:4, e:6}, i = 6
    {o:1, v:2, l:4, e:5, t:7} => {v:2, l:4, e:5, t:7}, i = 7
    left = 1, right = 7
    ...
    max = right - left
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
//        if (k == 0) return 0;
//        int len = s.length();
//        int left = 0, right = 0;
//        Map<Character, Integer> rightmostPosition = new LinkedHashMap<>();
//        int maxLen = 1;
//
//        while (right < len) {
//            Character character = s.charAt(right);
//            // keep the key met to the rightmost
//            if (rightmostPosition.containsKey(character)) {
//                rightmostPosition.remove(character);
//            }
//            rightmostPosition.put(character, right++);
//
//            // if keysize > k, remove the leftmost key
//            if (rightmostPosition.size() == k + 1) {
//                Map.Entry<Character, Integer> leftMost = rightmostPosition.entrySet().iterator().next();
//                rightmostPosition.remove(leftMost.getKey());
//                left = leftMost.getValue() + 1;
//            }
//
//            maxLen = Math.max(maxLen, right - left);
//        }
//        return maxLen;
        if (s == null || k == 0) return 0;

        int len = s.length(), num = 0, start = 0, ret = 0;
        int count[] = new int[256];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (count[c]++ == 0) {
                num++;
            }

            // update start to keep the num == k
            while (num > k) {
                if (--count[s.charAt(start++)] == 0) {
                    num--;
                }
            }
            ret = Math.max(ret, i - start + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        LeetCode340 solution = new LeetCode340();
        String s = "loveleetcode";
        int k = 4;
        int res = solution.lengthOfLongestSubstringKDistinct(s, k);
        System.out.println(res);
    }
}
