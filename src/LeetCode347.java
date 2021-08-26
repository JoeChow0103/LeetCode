import java.util.*;

public class LeetCode347 {
//    public int[] topKFrequent(int[] nums, int k) {
        // using heap, Time O(Nlogk), O(N+k) space
//        int[] result = new int[k];
//        if (nums == null || nums.length == 0 || k <= 0) return result;
//
//        Map<Integer, Integer> map = new HashMap<>();
//        PriorityQueue<Integer> heap = new PriorityQueue<>(
//                k, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer i1, Integer i2) {
//                if (map.get(i1).equals(map.get(i2))) return 0;
//                return map.get(i1) > map.get(i2) ? 1 : -1;
//            }
//        }
//        );
//
//        for (int i : nums) {
//            map.put(i, map.getOrDefault(i, 0) + 1);
//        }
//
//        for (int key : map.keySet()) {
//            if (heap.size() < k) {
//                heap.offer(key);
//            } else  {
//                int freq = map.get(key);
//                if (freq > map.get(heap.peek())) {
//                    heap.poll();
//                    heap.offer(key);
//                }
//            }
//        }
//
//        for (int i = 0; i < k; i++) {
//            result[i] = heap.poll();
//        }
//
//        System.out.println(heap.toString());
//
//        return result;

    public int[] topKFrequent(int[] nums, int k) {
        // using quickSelect O(N) average, O(N^2) worst case, O(N) space
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Pair[] list = new Pair[map.size()];
        int i = 0;
        for (int n : map.keySet()) {
            list[i++] = new Pair(n, map.get(n));
        }

        int idx = findKth(list, 0, list.length - 1, k);
        int[] res = new int[k];
        for (int j = 0; j <= idx; j++) {
            res[j] = list[j].val;
        }
        return res;
    }

    private int findKth(Pair[] list, int start, int end, int k) {
        if (start == end) return start;

        int pivotIdx = partition(list, start, end);
        int rank = pivotIdx - start + 1;

        if (rank == k) {
            return pivotIdx;
        } else if (rank < k) {
            return findKth(list, pivotIdx + 1, end, k - rank);
        } else {
            return findKth(list, start, pivotIdx - 1, k);
        }
    }

    private int partition(Pair[] list, int start, int end) {
        Pair pivot = list[end];
        int left = start - 1;
        for (int i = start; i < end; i++) {
            Pair cur = list[i];
            if (cur.count > pivot.count) {
                swap(list, ++left, i);
            }
        }
        swap(list, ++left, end);
        return left;
    }

    private void swap(Pair[] list, int i, int j) {
        Pair temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    class Pair {
        public int val, count;

        public Pair(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}
