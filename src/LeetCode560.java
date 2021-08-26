import java.util.HashMap;

public class LeetCode560 {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int[] smallestSubarraySum(int[] nums, int k) {
        int sum = 0;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                len = i - map.get(sum - k);
                if (len < min) {
                    res[1] = i; // input's index
                    res[0] = map.get(sum - k) + 1; // presum's index
                }
            }
            map.put(sum, i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 2, 4, 4};
        int[] res = smallestSubarraySum(nums, 8);
        for (int i : res) System.out.println(i);
    }
}
