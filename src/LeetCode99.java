public class LeetCode99 {
    private TreeNode prev = null;

    private void check(TreeNode root, TreeNode[] mistake) {
        if (root == null) return;
        check(root.left, mistake);
        if (prev != null && prev.val > root.val) {
            // keep the FIRST and LAST violate node
            mistake[1] = root;
            if (mistake[0] == null) mistake[0] = prev;
        }
        prev = root;
        check(root.right, mistake);
    }

    public void recoverTree(TreeNode root) {
        TreeNode[] mistake = new TreeNode[2];
        mistake[0] = mistake[1] = null;
        check(root, mistake);
        if (mistake[0] != null && mistake[1] != null) {
            int temp = mistake[0].val;
            mistake[0].val = mistake[1].val;
            mistake[1].val = temp;
        }
    }
}
