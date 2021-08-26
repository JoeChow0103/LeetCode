import java.util.*;

public class LeetCode205 {
    /*
    idea is to use two HashMap to put char as key and index as value for two strings;
    then traverse strings, if the value are not the same then they are not isomorphic
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null) return t == null;
        if (t == null) return s == null;
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!mapS.containsKey(c1)) {
                mapS.put(c1, i);
            }
            if (!mapT.containsKey(c2)) {
                mapT.put(c2, i);
            }

            if (mapS.get(c1) != mapT.get(c2)) { // return the result before hit the last char
                return false;
            }
        }
        return true;
    }
}
