import java.util.*;

public class LeetCode332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        // build graph
        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> pair : tickets) {
            String from = pair.get(0), to = pair.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }

        search(res, "JFK", graph);

        return res;
    }

    private void search(List<String> res, String cur, HashMap<String, PriorityQueue<String>> graph) {
        PriorityQueue<String> nexts = graph.get(cur);
        while (nexts != null && !nexts.isEmpty()) {
            String next = nexts.poll();
            search(res, next, graph);
        }
        res.add(0, cur);
    }
}
