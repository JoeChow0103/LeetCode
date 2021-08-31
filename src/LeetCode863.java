import java.util.*;

public class LeetCode863 {
    HashMap<TreeNode, TreeNode> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // dfs record the relation
        dfs(root, null);

        // bfs search the neighbor
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(null);
        queue.offer(target);
        int dis = 0;
        HashSet<TreeNode> set = new HashSet<>();
        set.add(null);
        set.add(target);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                if (dis == k) {
                    List<Integer> result = new ArrayList<>();
                    while (!queue.isEmpty()) {
                        result.add(queue.poll().val);
                    }
                    return result;
                }
                queue.offer(null);
                dis++;
            } else {
                // left
                if (set.add(cur.left)) {
                    queue.offer(cur.left);
                }
                // right
                if (set.add(cur.right)) {
                    queue.offer(cur.right);
                }
                // parent
                TreeNode par = map.get(cur);
                if (set.add(par)) {
                    queue.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    private void dfs(TreeNode node, TreeNode parent) {
        if (node != null) {
            map.put(node, parent);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
