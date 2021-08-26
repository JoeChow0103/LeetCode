import java.util.*;

public class LeetCode252 {
    /*
    if the first number of the next interval is smaller than the last number of a interval, then false;
     */
    public boolean canAttenMeeting(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return 0;
                }
                return a[0] < b[0] ? -1 : 1;
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
