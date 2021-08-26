import java.util.*;

public class LeetCode1167 {
    /**
     * sticks = [1,8,3,5,4,6,7] pick randomly
     * 1 + 3 = 4 [4,8,5,4,6,7] cost = 1 & 3 = 4
     * 4 + 4 = 8 [8,8,5,6,7]
     * 5 + 6 = 11 [11,8,8,7]
     * [15,11,8],[19,15],[34]
     */
//    public int connectSticks(int[] sticks) {
//        //c.c
//        if (sticks == null) return -1;
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        for (int i = 0; i < sticks.length; i++) {
//            minHeap.offer(sticks[i]);
//        }
//        int cost = 0;
//        while (minHeap.size() > 1) {
//            int val = minHeap.poll() + minHeap.poll();
//            cost += val;
//            minHeap.offer(val);
//        }
//        return cost;
//    }
    public int connectSticks(int[] sticks) {
        //c.c
        return dfs(sticks, 0, sticks.length - 1);
    }

    private int dfs(int[] sticks, int i, int j) {
        // base case
        if (i == j) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int cur = 0; cur < j; cur++) {
            int res1 = dfs(sticks, i, cur);
            int res2 = dfs(sticks, cur + 1, j);
            res = Math.min(res, res1 + res2);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1167 solution = new LeetCode1167();
        int[] sticks = new int[]{1,8,3,5,4,6,7};
        int res = solution.connectSticks(sticks);
        System.out.println(res);
    }

}
