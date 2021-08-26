import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) return res;
        int num  = words.length, uLen = words[0].length();
        if (s.length() < num * uLen) return res;

        // see how many unique word in words
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < num; i++) {
            String str = words[i];
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
                count++;
            }
        }

        HashMap<String, Integer> curMap = new HashMap<>();
        for (int i = 0; i < uLen; i++) {
            curMap.clear();
            int start = i, end = i + num * uLen;
            if (end > s.length()) break;
            int curCount = initial(map, curMap, s, start, end, uLen);
            if (curCount == count) res.add(start);
            while (end  + uLen <= s.length()) {
                end += uLen;
                String prev = s.substring(start, start + uLen);
                String next = s.substring(end - uLen, end);

                if (curMap.containsKey(prev)) {
                    if (curMap.get(prev) == 0) curCount--;
                    curMap.put(prev, curMap.get(prev) - 1);
                    if (curMap.get(prev) == 0) curCount++;
                }
                if (curMap.containsKey(next)) {
                    if (curMap.get(next) == 0) curCount--;
                    curMap.put(next, curMap.get(next) + 1);
                    if (curMap.get(next) == 0) curCount++;
                }

                if (count == curCount) res.add(start + uLen);
                start += uLen;
            }
        }
        return res;
    }

    public int initial(HashMap<String, Integer> map, HashMap<String, Integer> curMap,
                       String s, int start, int end, int uLen) {
        int count = 0;
         for (Map.Entry<String, Integer> e : map.entrySet()) {
             curMap.put(e.getKey(), 0 - e.getValue());
         }
         for (int i = start; i + uLen <= end; i += uLen) {
             String str = s.substring(i, i + uLen);
             if (map.containsKey(str)) {
                 if (curMap.get(str) == 0) count--;
                 curMap.put(str, curMap.get(str) + 1);
                 if (curMap.get(str) == 0) count++;
             }
         }
         return count;
    }
}
