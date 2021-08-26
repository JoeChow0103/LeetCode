public class LeetCode156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case
        if (root == null) return null;
        if (root.left == null) return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        // de-cycle
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
