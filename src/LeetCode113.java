import java.util.ArrayList;
import java.util.List;

public class LeetCode113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        search(root, new ArrayList<>(), res, targetSum);
        return res;
    }

    public void search(TreeNode root, List<Integer> path, List<List<Integer>> res, int remainingSum) {
        if (root == null) return;

        path.add(root.val);
        if (remainingSum == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            search(root.left, path, res, remainingSum - root.val);
            search(root.right, path, res, remainingSum - root.val);
        }
        path.remove(path.size() - 1);
    }
}
