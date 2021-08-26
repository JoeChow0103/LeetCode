import java.util.*;

public class LeetCode380 {
    /*
    cannot random without a id, so need to record the numbers' id when store
     */
    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random rand;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }

            map.put(val, list.size());
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            //System.out.println("removing " + val);

            int removeIndex = map.get(val);
            //System.out.println("removeIndex=" + removeIndex);


            map.put(list.get(list.size() - 1), removeIndex);
            map.remove(val);

            list.set(removeIndex, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            //System.out.println(map.size());
            //System.out.println(list);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));

        }
    }
}
