public class LeetCode98 {
    /*
    the property of BST is the left-child < root < right -child, and each subtree is a BST.
    lower bound for a root is left_child, upper bound for a root is right-child.
    ask isValid from the root, don't know; then ask it's left child, right child.
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE); // TODO Long
    }

    private boolean dfs(TreeNode root, int lower, int upper) {
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;
        return dfs(root.left, lower, root.val) && dfs(root.right, root.val, upper);
    }
}
