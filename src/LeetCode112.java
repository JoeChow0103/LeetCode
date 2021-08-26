public class LeetCode112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // return search(root, 0, targetSum);
        if (root == null) return false;
        targetSum -= root.val;
        // arrive to leaf node, check if targetSum == root.val;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

//    private boolean search(TreeNode root, int cur, int sum) {
//        if (root == null) return false;
//        int val = root.val + cur;
//        if (root.left == null && root.right == null) {
//            if (val == sum) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        boolean left = search(root.left, val, sum);
//        if (left) {
//            return true;
//        } else {
//            return search(root.right, cur + root.val, sum);
//        }
//    }
}
