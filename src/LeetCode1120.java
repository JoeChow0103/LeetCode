public class LeetCode1120 {
    class DataNode {
        int sum;
        int count;

        public DataNode(int sum, int count) {
            this.count = count;
            this.sum = sum;
        }
    }

    double max = Double.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        max = 0;
        findMax(root);
        return max;
    }

    private DataNode findMax(TreeNode root) {
        if (root == null) return new DataNode(0, 0);
        DataNode left = findMax(root.left);
        DataNode right = findMax(root.right);

        int total = root.val + left.sum + right.sum;
        int count = 1 + left.count + right.count;

        double currentAverage = total / (double) count;
        max = Double.max(max, currentAverage);

        DataNode newNode = new DataNode(total, count);
        return newNode;
    }
}
