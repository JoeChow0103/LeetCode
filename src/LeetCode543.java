public class LeetCode543 {
    /**
     * the length of the longest path to 2 leaf nodes for one parent node
     */
    private int max = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return max;
    }

    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);
        max = Math.max(max, left + right); // update diameter of a node
        return Math.max(left, right) + 1; // return the longest one of left and right, add one since connect to parent
    }
}
