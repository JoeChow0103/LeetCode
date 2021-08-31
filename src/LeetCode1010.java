import java.util.HashMap;

public class LeetCode1010 {
    public int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int t : time) {
            if (t %  60 == 0) count += map.getOrDefault(0, 0);
            if (map.containsKey(60 - t % 60)) count += map.get(60 - t % 60);
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return count;
    }
}
