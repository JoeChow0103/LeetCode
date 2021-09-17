import java.util.HashSet;

public class LeetCode128 {
    public int longestConsecutive(int[] nums) {
        // set to store the nums
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        // res is to find the max len of consecutives
        int res = 0;
        for (int num : nums) {
            int count = 1;
            set.remove(num);
            // find the all the connectives less ten num
            int l = num - 1;
            while (set.contains(l)) {
                count++;
                set.remove(l);
                l--;
            }
            // find all the connectives greater then num
            int r = num + 1;
            while (set.contains(r)) {
                count++;
                set.remove(r);
                r++;
            }
            // update the res by compare the count and res pick the larger
            res = Math.max(res, count);
        }
        return res;
    }
}
