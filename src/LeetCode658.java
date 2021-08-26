import java.util.*;

public class LeetCode658 {
    /*
    two pointer left & right. if left is closer, move right, otherwise move left.
    until left - right < k.
    Time: O(n), Space: O(1)
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> output = new ArrayList<>();
        if (arr == null || arr.length == 0) return output;

        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }

        for (int i = lo; i <= hi; i++) {
            output.add(arr[i]);
        }
        return output;
    }
}
