import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};

public class LeetCode759 {
    /*
    all the intervals are need. After sorted, if the last number of an interval is smaller than the first number of
    the next interval, then the real interval is between these two numbers.
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((c1, c2) -> (c1.start - c2.start));

        for (List<Interval> employee : schedule) {
            for (Interval inter : employee) {
                minHeap.offer(inter);
            }
        }

        int max = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            Interval top = minHeap.poll();
            if (max != Integer.MIN_VALUE && top.start > max) {
                // find the free period
                res.add(new Interval(max, top.start));
            }
            max = Math.max(max, top.end);
        }
        return res;
    }
}
