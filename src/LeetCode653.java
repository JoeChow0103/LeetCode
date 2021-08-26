import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode653 {
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (set.contains(cur.val)) {
                return true;
            } else {
                set.add(k - cur.val);
            }
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return false;
    }
}
