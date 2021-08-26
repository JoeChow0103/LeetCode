import java.util.*;

public class LeetCode403 {
    /**
     * pruning can be boolean[idx][k] or HashMap<k, boolean>[stones.idx]
     * boolean[k] vs. HashMap<k, boolean>
     * 1. when k is large, boolean array will cost too much, some k is not used then will be wasted.
     * which is k*idx space, but the HashMap only use much less.
     * 2. in general, if the mem is to store something continuous, use array.
     * @param stones
     * @return
     */
//    public boolean canCross(int[] stones) {
//        // c.c.
//        if (stones == null || stones.length <= 1) return false;
//        if (stones[1] - stones[0] != 1) return false;
//
//        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length];
//        for (int i = 0; i <stones.length; i++) {
//            mem[i] = new HashMap<Integer, Boolean>();
//        }
//
//        return dfs(stones, 1, 1, mem); // start from idx = 1, so corner case must contains idx = 1 & 0
//    }
//
//    private boolean dfs(int[] stones, int idx, int k, HashMap<Integer, Boolean>[] mem) {
//        // base case
//        // success, no failure case since if fail, it cannot goes to the end
//        int len = stones.length;
//        if (idx == len - 1) return true; // start from 0
//
//        HashMap<Integer, Boolean> map = mem[idx];
////        System.out.println(idx);
////        System.out.println(map);
//        if (map.containsKey(k)) return map.get(k);
//
//        for (int i = idx + 1; i < len; i++) { // start from the next stone
//            int dis = stones[i] - stones[idx];
//            if (dis < k - 1) continue;
//            if (dis > k + 1) break;
//
//            if (dis == k - 1 || dis == k || dis == k + 1) {
//                if (dfs(stones, i, dis, mem)) {
//                    map.put(k, true);
//                    return true;
//                }
//            }
//        }
//        map.put(k, false);
//        return false;
//    }

    // dp(i, j) is to store the distance from i to j
//    public boolean canCross(int[] stones) {
//        int len = stones.length;
//        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
//        for (int i = 0; i < len; i++) {
//            dp.put(i, new HashSet<>());
//        }
//        dp.get(0).add(0);
//        for (int i = 0; i < len; i++) {
//            for (int k : dp.get(i)) {
//                for (int j = i + 1; j < len; j++) {
//                    int diff = stones[j] - stones[i]; // last jump units
//                    if (diff > k + 1) break;
//                    if (diff < k - 1) continue;
//                    dp.get(j).add(diff);
//                    if (j == len - 1) return true;
//                }
//            }
//        }
//        return false;
//    }

    public boolean canCross(int[] stones) {
        int len = stones.length;
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
        for (int i = 0; i < len; i++) {
            dp.put(stones[i], new HashSet<>());
        }
        dp.get(0).add(0);
        for (int i = 0; i < len; i++) {
            for (int k : dp.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && dp.containsKey(stones[i] + step)) {
                        dp.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return dp.get(stones[len - 1]).size() > 0;
    }

    public static void main(String[] args) {
        LeetCode403 solution = new LeetCode403();
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        boolean res = solution.canCross(stones);
        System.out.printf(String.valueOf(res));
    }
}
