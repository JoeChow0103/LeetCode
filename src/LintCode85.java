public class LintCode85 {
    /*
    insert a node into the binary search tree
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) return node;

        TreeNode cur = root;
        while (true) {
            if (cur.val < node.val) {
                if (cur.right == null) {
                    cur.right = node;
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    cur.left = node;
                    break;
                } else {
                    cur = cur.left;
                }
            }
        }
        return root;
    }
}
