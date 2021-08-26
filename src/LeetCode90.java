import java.util.*;

public class LeetCode90 {
    /**
     * assure sorted
     * [1,2,2] DFS first method
     *                     {}
     *           {1}      {2}     {2} // duplicate
     *     {1,2} {1,2}  {2,2}
     * {1,2,2}
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        // corner case
        if (nums == null) {
            return res;
        }

        res.add(new ArrayList<>());
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (level == nums.length) {
            return;
        }
        for (int i = level; i < nums.length; i++) {
            if (i != level && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            dfs(nums, i + 1, list, res); // wall
            list.remove(list.size() - 1);
        }
    }

    /**
     * [1,2,3] DFS second method
     *                        {}
     *               {1}                     {}
     *     {1,2}          {1}          {2}       {}
     * {1,2,2} {1,2}   {1,2} {1}    {2,2} {2}  {2}  {} // duplicate
     */

//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);
//        // corner case
//        if (nums == null) {
//            return res;
//        }
//
//        dfs(nums, 0, new ArrayList<>(), res);
//        return res;
//    }
//
//    private void dfs(int[] nums, int level, List<Integer> list, List<List<Integer>> res) {
//        // base case
//        if (level == nums.length) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        // case 1: add element on level
//        list.add(nums[level]);
//        dfs(nums, level + 1, list, res);
//        // backtracking
//        list.remove(list.size() - 1); // cannot use list.remove(level) may have case: {1,2} on level = 3
//        // case 2: not add element on level
//        if (list.size() == 0 || list.get(list.size() - 1) != nums[level])
//        dfs(nums,level + 1, list, res);
//    }

    public static void main(String[] args) {
        LeetCode90 solution = new LeetCode90();
        int[] nums = {1,2,2};
        List<List<Integer>> res = solution.subsetsWithDup(nums);
        System.out.println(res);
    }
}
