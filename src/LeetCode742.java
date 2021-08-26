import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode742 {
    /*
    bfs, build graph search up and down
     */
    public int findClosestLeaf(TreeNode root, int k) {
        // step 1: build graph
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        // key: current node
        // value: parent node
        buildGraph(root, null, map);

        // step 2: find target node
        TreeNode targetNode = null;
        for (TreeNode node : map.keySet()) {
            if (node.val == k) {
                targetNode = node;
                break;
            }
        }

        // step 3: bfs of graph
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(targetNode);
        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(targetNode);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null && cur.right == null) {
                // leaf node
                return cur.val;
            }
            if (cur.left != null && visited.add(cur.left)) {
                queue.offer(cur.left);
            }
            if (cur.right != null && visited.add(cur.right)) {
                queue.offer(cur.right);
            }
            if (map.get(cur) != null && visited.add(map.get(cur))) {
                queue.offer(map.get(cur));
            }
        }
        return -1;
    }

    private void buildGraph(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        if (root == null) return;
        map.put(root, parent);
        buildGraph(root.left, root, map);
        buildGraph(root.right, root, map);
    }

}
