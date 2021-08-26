public class LeetCode298 {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);

        int local = 1; // initial as 1, when leaf node
        if (root.left != null && root.val + 1 == root.left.val) {
            local = left + 1;
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            local = Math.max(local, right + 1);
        }

        max = Math.max(local, max);
        return local;
    }
}
