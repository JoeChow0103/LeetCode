import java.util.*;

public class LeetCode1353 {
    /*
    Greedy, sort event by endtime. If curtime > endtime, there is no way to attend that metting anymore.
    For each (sorted) event, find the first available day (if exist) to attend
    12
    12
      3
    12345
    12345

     */
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (e1, e2) -> Integer.compare(e1[1], e2[1]));
        int count = 0;
        int n = events[events.length - 1][1];
        int[] days = new int[n + 1];
        for (int[] event : events) {
            int start = event[0], end = event[1];
            for (int i = start; i <= end; i++) {
                if (days[i] > 0) {
                    continue;
                } else {
                    days[i]++;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] events = {{1,2},{1,2},{2,3}, {3,4}};
        System.out.println(maxEvents(events));
    }
}
