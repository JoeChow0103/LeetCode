public class LeetCode101 {
    /*
    1. node.left == node.right;
    2. node.left.left == node.right.right; node.left.right == node.right.left;
    3. node.left.left.left == node.right.right.right; node.left.left.right == node.right.right.left;
    node.left.right.left == node.right.left.right; node.left.right.right == node.right.right.left;

    Two trees are a mirror reflection of each other if:
        1. Their two roots have the same value.
        2. The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
