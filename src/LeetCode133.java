import java.util.*;

class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class LeetCode133 {
    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;
        HashMap<GraphNode, GraphNode> map = new HashMap<>();
        return helper(node, map);
    }

    private GraphNode helper(GraphNode node, HashMap<GraphNode, GraphNode> map) {
        if (node == null) return null;
        if (map.containsKey(node)) {
            return map.get(node);
        }
        GraphNode copy = new GraphNode(node.val);
        map.put(node, copy);
        for (GraphNode n : node.neighbors) {
            copy.neighbors.add(helper(n, map));
        }
        return copy;
    }
}
