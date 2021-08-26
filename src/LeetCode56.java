import java.util.*;

public class LeetCode56 {
    /*
    With a list of interval sorted by the first number of the interval, compare the last number of a interval to the
    first number of the next interval. If it's bigger, then merge into one, then compare to the next.
    Otherwise, directly put into the result
    Time: O(nlogn), Space(n)
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < intervals.length; i++) {
            if (stack.isEmpty() || stack.peek()[1] < intervals[i][0]) {
                stack.push(intervals[i]);
            } else {
                int[] temp = stack.pop();
                temp[1] = Math.max(temp[1], intervals[i][1]);
                stack.push(temp);
            }
        }

        int[][] res = new int[stack.size()][];
        for (int i = res.length - 1; i >= 0; i --) {
            res[i] = stack.pop();
        }
        return res;
    }
}
