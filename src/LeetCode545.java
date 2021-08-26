import java.util.*;

public class LeetCode545 {
    /**
     * left subtree use preOrder
     * right subtree use postOrder
     * check if leaf node
     * @param root
     * @return
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // c.c.
        if (root == null) return res;

        TreeNode cur = root;
        if (!isLeaf(root)) {
            res.add(root.val);
            cur = cur.left;
        }
        // add left
        while (cur != null && !isLeaf(cur)) {
            res.add(cur.val);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        // add leaf
        addLeaf(root, res);

        // add right
        Stack<TreeNode> stack = new Stack<>();
        cur = root.right;
        while (cur != null && !isLeaf(cur)) {
            stack.push(cur);
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            res.add(temp.val);
        }

        return res;
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private void addLeaf(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (isLeaf(root)) {
            res.add(root.val);
        }
        addLeaf(root.left, res);
        addLeaf(root.right, res);
    }
}
