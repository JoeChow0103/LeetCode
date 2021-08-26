public class LeetCode333 {
    /*
    Bruteforce: check left right is BST, then compare the size.
    Time: O(N^2) one is check is BST, other is calculate the size;
    Space: O(1);
    Improvement: do the same at the same time, use a wrapper
    Time: O(N), Space: O(N);
     */
    class Result {
        public int min, max, size;

        public Result(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    private int maxSize;

    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return maxSize;
    }

    private Result helper(TreeNode root) { // O(N);
        if (root == null) return new Result(0, 0, 0);

        Result left = helper(root.left);
        Result right = helper(root.right);

        if (left == null || right == null) return null;

        int size = 1;
        // update cur size
        if ((left.size == 0 || left.max < root.val)
        && (right.size == 0 || right.min > root.val)) { // check if a BST
            size = left.size + 1 + right.size;
            maxSize = Math.max(maxSize, size);
        } else { // Not BST
            return null;
        }

        // update root's min max
        int min = (left.size > 0) ? left.min : root.val;
        int max = (right.size > 0) ? right.max : root.val;

        return new Result(min, max, size);
    }

//    public int largestBSTSubtree(TreeNode root) {
//
//    }
//
//    private Node helper(TreeNode root, int[] res) {
//
//    }

//    public int largestBSTSubtree(TreeNode root) { // O(N)*O(N+N)
//        if (root == null) return 0;
//        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
//            return size(root);
//        }
//        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
//    }
//    public int size(TreeNode root) {//O(n)
//        if (root == null) return 0;
//        return size(root.left) + size(root.right) + 1;
//    }
//    public boolean isBST(TreeNode root, int min, int max) {//O(n)
//        if (root == null) return true;
//        if (root.val <= min || root.val >= max) return false;
//        // no duplicate in BST
//        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
//    }

}
