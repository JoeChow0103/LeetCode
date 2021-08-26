import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Topology {
    /*
    several nodes' local relation to deduct the global relation for all nodes
     */
    public List<V> containsCycle(HashMap<V, List<V>> graph) {
        List<V> res = new ArrayList<>();
        //c.c
        for (V start : graph.keySet()) {
            if (containsCycle(start, graph, res)) {
                throw new RuntimeException("has cycle");
            }
        }
        return res;
    }

    private boolean containsCycle(V cur, HashMap<V, List<V>> graph, List<V> res) {
        if (cur.status == Status.VISITED) { // pruning
            return false;
        }
        if (cur.status == Status.VISITING) { // cycle
            return true;
        }

        cur.status = Status.VISITING;
        for (V next : graph.get(cur)) {
            if (containsCycle(next, graph, res)) {
                return true;
            }
        }
        cur.status = Status.VISITED;
        res.add(0, cur);
        return false;
    }
}
