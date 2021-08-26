import java.util.*;

public class LeetCode47 {
    /**
     *           {1,1,2}
     * (1)      {1,1,2} swap(1,1)                        {1,1,2} swap(1,2)          {2,1,1}
     * (2) {1,1,2} swap(2,2) {1,2,1} swap(2,3)      {1,1,2} swap(2,2) {1,2,1}   swap(2,3)  {2,1,1} {2,1,1}
     * (3) {1,2,3} swap(3,3)
     * the temp permutation of first 1 and second 1 duplicate, thus we need duplicate
     * this time we can just use set
     * a b1 b2 b3 c Ajacent
     * 					root (abbbc)
     * 			/	                |             \
     * 		          a(bbbc)     	b1(abbc)   b2babc    b3bbac              c(bbba)
     * 		        /     \	       	 /     \               				/     \
     *     a(bbbc)  a(cbbb)  b1a(bbc) b1b2(abc) b1b3(b2ac) b1c(bba)
     * 		        |      |       |       |
     * 		     abc  acb ……..
     *  always pick the first one if the elements has duplicates.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //c.c.
        if (nums == null) return res;

        Arrays.sort(nums);
        dfs(nums, res, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int index) {
        // base case
        if (index == nums.length - 1) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <nums.length; j++) {
                temp.add(nums[j]);
            }
            res.add(temp);
            return;
        }

        // if (i > 0 && nums[i] == nums[i - 1] && !set.contains(i - 1)) continue;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            if (set.add(nums[i])) {
                swap(nums, index, i);
                dfs(nums, res, index + 1);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LeetCode47 solution = new LeetCode47();
        int[] nums = {1,1,2,3};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println(res);
    }
}