import java.util.*;

public class LeetCode460 {
}

class LFUCache {

    private final Map<Integer, Integer> keyToVal;
    private final Map<Integer, Integer> countMap; // value -count
    private final Map<Integer, Set<Integer>> lists; // value- freq LinkedHashSet
    private int capacity;
    private int min;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        countMap = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
        this.capacity = capacity;
        min = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        int count = countMap.get(key);
        countMap.put(key, count + 1); //increase freq
        //delete old freq
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty()) {
            min++;
        }
        lists.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);//modify value
            get(key);
            return;
        }

        if (keyToVal.size() >= capacity) {
            int evit = lists.get(min).iterator().next(); //
            lists.get(min).remove(evit);
            keyToVal.remove(evit);
        }
        keyToVal.put(key, value);
        countMap.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
