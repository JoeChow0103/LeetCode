public class LeetCode285 {
    /*
    use the property of BST, cur.val <= p.val, cut the left subtree; otherwise, find in the left subtree
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                prev = cur;
                cur = cur.left;
            }
        }
        return prev;
    }
}
