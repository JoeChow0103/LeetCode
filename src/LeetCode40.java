import java.util.*;

class LeetCode40 {
    /**
     * {2, 5, 2, 1, 2} target = 5;
     * res has no duplicates
     * after sorted, [1, 2a, 2b, 2c, 5], target = 5
     * [1, 2, 2] may be [1, 2a, 2b], [1, 2a, 2c] which are duplicates
     * so only used the first one
     *          [1]1                                                        [2]a   [2]b    [2]c   [5]5
     *         [1,2]a                  [1,2]b        [1,2]c [1,5]
     * [1,2,2]a [1,2,2]b [1,2,5]
     * [1,2,2,2]
     * deduplicate if (candidates[i] == candidates[i - 1]) on the same level
     * add (i > index) let the compare keep the previous level from mess
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //c.c
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, int sum, List<Integer> list, List<List<Integer>> res) {
        // base case
        if (sum > target) { // dont' need index >= candidates.length, which conflict element == target in the end
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            dfs(candidates, target, i + 1, sum + candidates[i], list, res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode40 solution = new LeetCode40();
        int[] candidates = {1,2,2,2,5,6};
        int target = 6;
        List<List<Integer>> res = solution.combinationSum2(candidates, target);
        System.out.println(res);
    }
}
