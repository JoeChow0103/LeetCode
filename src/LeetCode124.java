public class LeetCode124 {
    /**
     * the path could be the node it self, or node.left + node, node.right + node, node + node.left + node.right
     */
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return max;
    }

    private int traverse(TreeNode root) { // inorder
        // base case
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);

        int val = Math.max(left + root.val, Math.max(right + root.val, root.val));

        max = Math.max(max, Math.max(val, left + right + root.val));

        return val;
    }

}
