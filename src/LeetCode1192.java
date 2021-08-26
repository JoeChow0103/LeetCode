import java.util.*;

public class LeetCode1192 {
    int t = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> conn : connections) {
            graph.get(conn.get(0)).add(conn.get(1));
            graph.get(conn.get(1)).add(conn.get(0));
        }

        // an array to save the timestamp that we meet a certain node
        int[] timestamps = new int[n];
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, 0, -1, graph, timestamps);
        return res;
    }

    // return the mininum timestamp started from cur in the graph
    private int dfs(List<List<Integer>> res, int cur, int prev, List<List<Integer>> graph, int[] ts) {
        if (ts[cur] > 0) {
            return ts[cur];
        }
        ts[cur] = t++;
        int minTimestamp = Integer.MAX_VALUE;
        for (int next : graph.get(cur)) {
            if (next == prev) { // ignore the edge where 'cur' comes from prev
                continue;
            }
            int neighborTimestamp = dfs(res, next, cur, graph, ts);
            minTimestamp = Math.min(minTimestamp, neighborTimestamp);
        }

        // determine whether (prev, cur) is a critical edge
        if (minTimestamp >= ts[cur]) {
            if (prev >= 0) {
                res.add(Arrays.asList(prev, cur));
            }
        }
        return Math.min(ts[cur], minTimestamp);
    }
}
