public class LeetCode270 {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        TreeNode closet = root;
        TreeNode cur = root;
        while (cur != null) {
            if (target == cur.val) return cur.val;
            if (Math.abs(cur.val - target) < Math.abs(closet.val - target)) {
                closet = cur;
            }
            cur = cur.val > target ? cur.left : cur.right;
        }
        return closet.val;
    }
}
