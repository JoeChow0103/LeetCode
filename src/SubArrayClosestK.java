import java.util.*;

public class SubArrayClosestK {
    public static int subarrayClosetK(int[] nums, int k) {
        int sum = 0;
        TreeMap<Integer, Integer>  map = new TreeMap<>();
        map.put(0, 1);
        int closetMin = Integer.MAX_VALUE, closetMax = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                return k;
            } else {
                if (map.higherEntry(sum - k) != null) {
                    closetMax = Math.max(sum - map.higherKey(sum - k), closetMax);
                }
                if (map.lowerEntry(sum - k) != null) {
                    closetMin = Math.min(sum - map.lowerKey(sum - k), closetMin);
                }
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        if (Math.abs(closetMax - k) <= Math.abs(closetMin - k)) {
            return closetMax;
        } else {
            return closetMin;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, -1, 5, -3, -2 };
        int res = subarrayClosetK(nums, 7);
        System.out.println(res);
    }
}
