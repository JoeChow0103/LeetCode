import java.util.*;

public class LeetCode230 {
    /*
    inorder traverse
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) return -1;
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list.get(k - 1);
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
