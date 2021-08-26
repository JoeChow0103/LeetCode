public class LeetCode235 {
    /**
     * clarify: a node to be a descendant of itself.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            return helper(root, q, p);
        }
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return helper(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return helper(root.right, p, q);
        } else {
            return root;
        }
    }
}
