import java.util.*;

public class LeetCode387 {
    /*
    Solution 1:
    HashMap / int[256] vs int[128] vs int[26]
    traverse 1st time to build hashmap<key = character, value = count>
    traverse 2nd time to find the first character with count 1, map.get(chars[i]) == 1, return chars[i]
    Time O(n)	Space O(k)
    set.add() → 1 vs >1
    Solution 2:
    Bit_map, integer's bit representation 2pass    → Boolean[26]
     */
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0){
            return -1;
        }

        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(count[c - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
