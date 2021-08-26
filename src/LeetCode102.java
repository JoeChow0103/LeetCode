import java.util.*;

public class LeetCode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> one = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                one.add(cur.val);

                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }

            res.add(one);
        }
        return res;
    }
}
