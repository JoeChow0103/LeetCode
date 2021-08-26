public class LeetCode129 {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        search(root, 0);
        return sum;
    }

    private void search(TreeNode root, int cur) {
        if (root == null) {
            return;
        }

        cur = cur * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += cur;
            return;
        }

        search(root.left, cur);
        search(root.right, cur);
    }
}
