import java.util.*;

public class LeetCode513 {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode ret = null;

        while (!queue.isEmpty()) {
            TreeNode firstNode = null;
            int size = queue.size();

            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (firstNode == null) firstNode = cur;

                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }

            ret = firstNode;
        }
        return ret.val;
    }
}
