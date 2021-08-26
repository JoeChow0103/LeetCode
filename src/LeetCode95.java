import java.util.*;

class LeetCode95 {
    public List<TreeNode> generateTrees(int n) {
        // corner case
        if (n <= 0) return new ArrayList<TreeNode>();
        return preOrder(1, n);
    }

    private List<TreeNode> preOrder(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) { // i as a root
            List<TreeNode> leftTree = preOrder(start, i - 1);
            List<TreeNode> rightTree = preOrder(i + 1, end);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    private static void preOrderTraverse(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public static void main(String[] args) {
        LeetCode95 solution = new LeetCode95();
        int n = 3;
        List<TreeNode> res = solution.generateTrees(n);
        for (TreeNode node : res) {
            System.out.println("start");
            preOrderTraverse(node);
            System.out.println("end");
        }
    }
}
