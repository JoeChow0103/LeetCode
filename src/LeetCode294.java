import java.util.*;

public class LeetCode294 {
    public boolean canWin(String currentState) {
        char[] cs = currentState.toCharArray();
        return canWin(cs, new HashMap<String, Boolean>());
    }

    private boolean canWin(char[] cs, HashMap<String, Boolean> map) {
        int len = cs.length;
        String str = String.valueOf(cs);
        if (map.containsKey(str)) return map.get(str);

        for (int i = 0; i < len -1; i++) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';

                boolean res = canWin(cs, map);
                cs[i] = '+';
                cs[i + 1] = '+';

                if (!res) {
                    map.put(new String(str), true);
                    return true;
                }
            }
        }
        map.put(new String(str), false);
        return false;
    }
}
