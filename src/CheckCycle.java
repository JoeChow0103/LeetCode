import java.util.*;

enum Status {
    INITIAL,
    VISITED,
    VISITING
}

class V {
    public int label;
    List<V> neighbours;
//    public boolean visited;
    public Status status;

    public V (int label) {
        this.label = label;
        this.neighbours = new ArrayList<>();
        status = Status.INITIAL;
    }
}

public class CheckCycle {
    /*
    Time: O(V*(V+E))
    improve: pruning, but only false can keep traverse.
    V status: Initial -> Visiting -> Visited: untouched, cycle, pruning
    draw a picture, when end turn visited, then back turn
    Time: O(V + E)
     */
//    public boolean containsCycle(HashMap<V, List<V>> graph) { //
//        // c.c.
//        HashMap<V, Boolean> mem = new HashMap<V, Boolean>();
//        HashSet<V> processed = new HashSet<>();
//        for (V cur : graph.keySet()) { // O(V)
//            if (containsCycle(cur, graph, new HashSet<V>(), mem)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean containsCycle(V cur, HashMap<V, List<V>> graph, HashSet<V> visited, HashMap<V, Boolean> mem) { // Time: O(V+E), traverse
//        if (visited.contains(cur)) {
//            return true;
//        }
//
//        Boolean ret = mem.get(cur);
//        if (ret != null) {
//            return ret;
//        }
//
//        visited.add(cur);
//
//        for (V next : graph.get(cur)) {
//            if (containsCycle(next, graph, visited, mem)) {
//                mem.put(cur, true);
//                return true;
//            }
//        }
//        visited.remove(cur);
//        mem.put(cur, false);
//        return false;
//    }

    public boolean containsCycle(HashMap<V, List<V>> graph) {
        //c.c
        for (V start : graph.keySet()) {
            if (containsCycle(start, graph)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsCycle(V cur, HashMap<V, List<V>> graph) {
        if (cur.status == Status.VISITED) { // pruning
            return false;
        }
        if (cur.status == Status.VISITING) { // cycle
            return true;
        }

        cur.status = Status.VISITING;
        for (V next : graph.get(cur)) {
            if (containsCycle(next, graph)) {
                return true;
            }
        }
        cur.status = Status.VISITED;
        return false;
    }
}
