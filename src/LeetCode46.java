import java.util.*;

public class LeetCode46 {
    /**
     *           {1,2,3}
     *       {1,2,3} swap(1,1)                        {2,1,3} swap(1,2)          {3,2,1}
     *  {1,2,3} swap(2,2) {1,3,2} swap(2,3)      {2,1,3} swap(2,2) {2,3,1} swap(2,3)  {3,2,1} {3,1,2}
     *  {1,2,3} swap(3,3)
     *  swap
     *  {1,2,3,4}
     *  (1)                 {1,2,3,4}               {2,1,3,4} {3,2,1,4} {4,2,3,1}
     *  (2)      {1,2,3,4}     {1,3,2,4}   {1,4,3,2}
     *  (3){1,2,3,4} {1,2,4,3}
     */
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        // c.c.
//        if (nums == null) return res;
//        dfs(nums, 0, res);
//        return res;
//    }
//
//    private void dfs(int[] nums, int level, List<List<Integer>> res) {
//        // base case
//        if (level == nums.length - 1) {
//            List<Integer> temp = new ArrayList<>();
//            for (int j = 0; j < nums.length; j++) {
//                temp.add(nums[j]);
//            }
//            res.add(temp);
//            return;
//        }
//
//        for (int i = level; i < nums.length; i++) {
//            swap(nums, level, i);
//            dfs(nums,  level + 1, res);
//            swap(nums, level, i);
//        }
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }

    /**
     * using hashset to avoid duplicate.
     * In other word, it pick the number then put into a list, then pick another. And so on.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // c.c.
        if (nums == null) return res;
        dfs(nums, res, new ArrayList<>(), new HashSet<Integer>(), 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, Set<Integer> set, int index) {
        // base case
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) continue;
            list.add(nums[i]);
            set.add(nums[i]);
            dfs(nums, res, list, set, i);
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
    }

    public static void main(String[] args) {
        LeetCode46 solution = new LeetCode46();
        int[] nums = {1,3,2};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res);
    }
}
