import java.util.*;

public class LeetCode464 {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger <= 0 || maxChoosableInteger > 20
                || desiredTotal < 0 || desiredTotal > 300) {
            throw new IllegalArgumentException();
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;

        int map = (1 << maxChoosableInteger) - 1;
        Boolean mem[] = new Boolean[map + 1];
        return helper(map, 0, desiredTotal, maxChoosableInteger, mem);
    }

    private boolean helper(int map, int curTotal, int desiredTotal,
                           int maxChoosableInteger, Boolean[] mem) {
        if (mem[map] != null) {
            return mem[map];
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((mask & map) != 0) {
                if (curTotal + i >= desiredTotal) {
                    return true;
                }

                int newMap = map - mask;
                if (!helper(newMap, curTotal + i, desiredTotal,
                        maxChoosableInteger, mem)) {
                    mem[map] = true;
                    return true;
                }
            }
        }
        mem[map] = false;
        return false;
    }
}
