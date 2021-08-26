public class LeetCode701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
            // if (root.left == null) root.left = new TreeNode(val);
        } else {
            root.right = insertIntoBST(root.right, val);
            // if (root.right == null) root.right = new TreeNode(val);
        }
        return root;
    }
}
