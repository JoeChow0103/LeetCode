import java.util.*;

public class LeetCode103 {
    /*
    level order traverse, left to right;
    change the order when put in the temp list: start from left, start from right, use a flag to determine where to start
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // cc
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (flag) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }
}
