import java.util.*;

public class LeetCode57 {
    /*
    compare the interval and newInterval.
    Three case:
    1. the interval don't be considered
    2. the interval need to be merged
    3. the interval has been merged and can be add to the result
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        Stack<int[]> stack = new Stack<>();

        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) { // the interval has been merged and can be add to result
                stack.push(newInterval);
                newInterval = interval;
            } else if (interval[1] >= newInterval[0]) { // the interval needs to be merged to one
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else { // the interval don't need to be considered
                stack.push(interval);
            }
        }

        stack.push(newInterval);
        int[][] res = new int[stack.size()][];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
