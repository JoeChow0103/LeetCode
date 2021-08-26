public class LeetCode114 {
    /*
    linked list use TreeNode class, in-order
    in place process the tail first.
    process tail first, so traverse to the right, then left
    recursively start from the most left subtree
     */
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode[] pre = new TreeNode[1];
        flatten(root, pre);
    }
    private void flatten(TreeNode root, TreeNode[] pre) {
        if (root == null) return;
        flatten(root.right, pre);
        flatten(root.left, pre);
        root.right = pre[0];
        root.left = null;
        pre[0] = root;
    }
}
