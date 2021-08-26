import java.util.PriorityQueue;

public class LeetCode703 {
    class KthLargest {
        private int k;
        private PriorityQueue<Integer> pq;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<>(k);
            for (int num : nums) {
                if (pq.size() < k) {
                    pq.offer(num);
                } else {
                    if (pq.peek() < num) {
                        pq.poll();
                        pq.offer(num);
                    }
                }
            }
        }

        public int add(int val) {
            if (pq.size() < k) {
                pq.offer(val);
            } else {
                if (pq.peek() < val) {
                    pq.poll();
                    pq.offer(val);
                }
            }
            return pq.peek();
        }
    }
}
