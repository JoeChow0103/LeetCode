import java.util.*;

public class LeetCode39 {
    /**
     * candidates = [2,3,6,7], target = 7
     * demo: list + sum
     * [2]2 [3]3 [6]6 [7]7
     * [2,2]4 [2,3]5 [2,6]8 [2,7]9
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // c.c.
        if(candidates == null || candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, int sum, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (sum > target || index >= candidates.length) {
            return;
        }

        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(candidates, target, i, sum + candidates[i], list, res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode39 solution = new LeetCode39();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = solution.combinationSum(candidates, target);
        System.out.println(res);
    }
}
