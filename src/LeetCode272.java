import java.util.*;

public class LeetCode272 {
    // stack; binary tree; in order; heap
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        inOrder(root, target, k, res);
        return res;
    }

    private void inOrder(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, target, k, res);
        if (k > res.size()) {
            res.add(root.val);
        } else {
            if (Math.abs(res.get(0) - target) > Math.abs(root.val - target)) {
                res.add(root.val);
                res.remove(0);
            }
        }
        inOrder(root.right, target, k, res);
    }
}
