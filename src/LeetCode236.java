public class LeetCode236 {
    /*
    tree traverse from top to bottom, the node find the ancestor is from bottom to top. Use the recursion to traverse bottom to top.
    If node is the p or q, return the node. if one of the child is p or q, then return p or q. (q, null) (p, null)
    If the l-child and r-child are p and q, then return the parent.

    follow up: if the p is q's LCA, q may not exist.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        if (p == q) return p;
        TreeNode left =  lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; // don't care where the p or q is
//        if (left != null) return left;
//        if (right != null) return right;
//        return null;

        return left == null ? right : left;
    }
}
