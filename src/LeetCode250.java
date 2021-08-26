public class LeetCode250 {
    /*
    divide and conquer. Base case, leaf node is a uni-val subtree
     */
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        isUniValTree(root, 0);
        return count;
    }
    private boolean isUniValTree(TreeNode root, int parentVal) {
        if (root == null) {
            return true;
        }

        boolean left = isUniValTree(root.left, root.val);
        boolean right = isUniValTree(root.right, root.val);
        if (!left || !right) {
            return false;
        }
        count++;
        return root.val == parentVal;
    }
}
