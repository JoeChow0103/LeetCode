import java.util.*;

public class LeetCode314 {
    /*
                        15 (root)
		                /		\
		                5	|	20
	                /   \      /        \
	               3     6     17       23
                /     \
            null    null
                   0    1     2         3
     level order, also need to record the col and node. And need match the col and node
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        // need start and end index to put into res
        int min = 0, max = 0;
        nodes.offer(root);
        cols.offer(0);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int col = cols.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);
            if (node.left != null) {
                nodes.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for(int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
