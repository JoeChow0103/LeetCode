import java.util.*;

public class LeetCode253 {
    /*
    how many overlap at the same time
     */
    public int minMeetingRooms(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0
                || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }

        // step 1: sort by start time
        Arrays.sort(intervals, (c1, c2) -> {
            return c1[0] - c2[0];
        });

        // step 2: put into minHeap, priority as end time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int count = 0;      // count of meeting rooms
        for (int[] interval : intervals) {
            while (!minHeap.isEmpty()
                    && minHeap.peek() <= interval[0]) {
                // new start time is larger or equal than minHeap min end time
                minHeap.poll();
            }
            minHeap.offer(interval[1]);     // offer new end time to minHeap
            count = Math.max(count, minHeap.size());
        }

        return count;
    }
}
