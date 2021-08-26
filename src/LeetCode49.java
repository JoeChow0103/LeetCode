import java.util.*;

public class LeetCode49 {
    /*
    The anagram sorted by chars will be the same. So use it as a key, and put the original string in str arrays as value
    Time: O(N*KLogK), Space: O(NK), K is the longest length of a string
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
//        Arrays.sort(strs); if need to output with order of dictionary
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String sortStr = String.valueOf(cs);
            if (!map.containsKey(sortStr)) {
                map.put(sortStr, new ArrayList<>());
            }
            map.get(sortStr).add(str);
        }

        List<List<String>> res = new ArrayList<>();
        res.addAll(map.values());
        return res;
    }
}
