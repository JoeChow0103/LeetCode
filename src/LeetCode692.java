import java.util.*;

public class LeetCode692 {
    /*
    We count the frequency of each word in O(N) time, then we add NN words to the heap,
    each in O(logk) time. Finally, we pop from the heap up to kk times.
    As k≤N, this is O(Nlogk) in total.
    Time: nlogk.
    Space: O(n)
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) return res;

        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> heap = new PriorityQueue<>(k, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (map.get(a).equals(map.get(b))) {
                    return b.compareTo(a); // for the queue FIFO, then b put the first, a put next
                }
                return map.get(a) > map.get(b) ? 1 : -1;
            }
        });

        for (String key : map.keySet()) {
            if (heap.size() < k) {
                heap.offer(key);
            } else {
                if (map.get(key) >= map.get(heap.peek())) { // remain the last one if duplicates
                    heap.offer(key);
                    heap.poll();
                }
            }
        }

        while (!heap.isEmpty()) {
            res.add(0, heap.poll());
        }
        return res;
    }
}
