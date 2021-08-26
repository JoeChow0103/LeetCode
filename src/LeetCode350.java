import java.util.*;

public class LeetCode350 {
    /*
    use a hashmap to put the number as a key and the count as a value for the shorter array.
    then one path traverse the shorter one to check if the value is bigger than 0;
    If not contains the key, then continue. If contains the key, value minus one.
    Time: O(m + n), Space = O(m), m is the smaller one
    Follow up: if the int[] is in disk.
    1. if the memory can handle then use load to the memory
    2. if the memory cannot handle, then split into subsequences, then do the solution multiple times
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // c.c.
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersect(nums2, nums1);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums1) map.put(n, map.getOrDefault(n, 0) + 1);

        List<Integer> res = new ArrayList<>(); // easy for insert and remove
        for (int n : nums2) {
            if (!map.containsKey(n)) continue;
            else {
                res.add(n);
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) map.remove(n); // in case get the overdo
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i <res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}
