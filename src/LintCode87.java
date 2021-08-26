public class LintCode87 {
    /*
    delete a node from a bst
     */
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null) return null;
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode parent = dummy, cur = root;
        while (cur != null) {
            if (cur.val == value) {
                remove(cur, parent);
                break;
            } else if (value < cur.val) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        return dummy.left;
    }

    private void remove(TreeNode node, TreeNode parent) {
        if (node.left == null && node.right == null) {
            replace(parent, node, null);
        } else if (node.left == null) {
            replace(parent, node, node.right);
        } else if (node.right == null) {
            replace(parent, node, node.left);
        } else { // remove rightmost node in the left subtree
            TreeNode par = node, rightMost = node.left;
            while (rightMost.right != null) {
                par = rightMost;
                rightMost = rightMost.right;
            }
            replace(par, rightMost, rightMost.left);
            // replace node with rightMost
            rightMost.left = node.left;
            rightMost.right = node.right;
            replace(parent, node, rightMost);
        }
    }

    private void replace(TreeNode root, TreeNode oldNode, TreeNode newNode) {
        if (root.left == oldNode) {
            root.left = newNode;
        } else {
            root.right = newNode;
        }
    }
}
