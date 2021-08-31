import java.util.*;

public class LeetCode1481 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());

        while(k > 0){
            int curr = pq.remove();
            k -= curr;
        }

        return k == 0 ? pq.size() : pq.size() + 1;
    }
}
