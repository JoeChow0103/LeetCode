import java.util.*;

public class LeetCode295 {
    class MedianFinder {
        /*
        minHeap and maxHeap, minHeap store the greater element, maxHeap store smaller element.
        It's possible that when offer a number to maxHeap causing max.size() - min.size() > 1.
        In order to keep the max.size() - min.size() <= 1 and min.size() < max.size(), do something to keep in the limit.
         */
        private PriorityQueue<Integer> max = new PriorityQueue<>();
        private PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder());
        private int size = 0;
        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {
            size++;
            if (max.size() == 0 || max.peek() < num) {
                max.offer(num);
                if (max.size() - min.size() > 1) {
                    min.offer(max.poll());
                }
            } else {
                min.offer(num);
                if (min.size() > max.size()) {
                    max.offer(min.poll());
                }
            }
        }

        public double findMedian() {
            if (size % 2 == 0) {
                return (double) (max.peek() + min.peek()) / 2.0;
            } else {
                return (double) (max.peek());
            }
        }
    }
}
