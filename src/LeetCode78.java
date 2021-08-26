import java.lang.reflect.Array;
import java.util.*;

public class LeetCode78 {
    /**
     * [1,2,3] DFS first method
     *                     {}
     *           {1}      {2}     {3}
     *     {1,2} {1,3}  {2,3}
     * {1,2,3}
     */
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        // corner case
//        if (nums == null) {
//            return res;
//        }
//
//        res.add(new ArrayList<>()); // in dfs, null will not be add, so add first
//        dfs(nums, 0, new ArrayList<Integer>(), res);
//        return res;
//    }
//
//    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res) {
//        // base case
//        if (level == nums.length) {
//            return;
//        }
//
//        for (int i = level; i < nums.length; i++) {
//            list.add(nums[i]);
//            res.add(new ArrayList<>(list));
//            dfs(nums, i + 1, list, res);
//            list.remove(list.size() - 1);
//        }
//    }

    /**
     * [1,2,3] DFS second method
     *                        {}
     *               {1}                     {}
     *     {1,2}          {1}          {2}       {}
     * {1,2,3} {1,2}   {1,3} {1}    {2,3} {2}  {3}  {}
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if(nums == null) {
            return res;
        }
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (level == nums.length) {
            res.add(new ArrayList<>(list)); // add the subset at the leaf node
            return;
        }

        // case 1: add element on level
        list.add(nums[level]);
        dfs(nums, level + 1, list, res);
        // backtracking
        list.remove(list.size() - 1); // cannot use list.remove(level) may have case: {1,2} on level = 3
        // case 2: not add element on level
        dfs(nums,level + 1, list, res);
    }

    public static void main(String[] args) {
        LeetCode78 solution = new LeetCode78();
        int[] nums = {1,2,3};
        List<List<Integer>> res = solution.subsets(nums);
        System.out.println(res);
    }
}
