import java.util.*;

public class LeetCode218 {

    private class EndPoint implements Comparable<EndPoint> {
        public int x, height;
        public boolean isEnd;

        public EndPoint(int x, int height, boolean isEnd) {
            this.x = x;
            this.height = height;
            this.isEnd = isEnd;
        }

        @Override
        public int compareTo(EndPoint that) {
            if (this.x != that.x) {
                return this.x - that.x;
            } else {
                if (!this.isEnd && !that.isEnd) { // both left points: first high
                    return that.height - this.height;
                } else if (this.isEnd && that.isEnd) {// both right points: first low
                    return this.height - that.height;
                } else { // one left and one right point: first left point
                    return this.isEnd ? 1 : -1;
                }
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();

        List<EndPoint> endPoints = new ArrayList<>();
        for (int[] building : buildings) {
            endPoints.add(new EndPoint(building[0], building[2], false));
            endPoints.add(new EndPoint(building[1], building[2], true));
        }

        Collections.sort(endPoints);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);

        for (EndPoint ep : endPoints) {
            if (!ep.isEnd) {// left point
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(Arrays.asList(ep.x, ep.height));
                }
                maxHeap.offer(ep.height);
            } else {// right point
                maxHeap.offer(ep.height);
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(Arrays.asList(ep.x, maxHeap.isEmpty() ? 0 : maxHeap.peek()));
                }
            }
        }
        return res;
    }
}
