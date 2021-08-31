import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeetCode547 {
    public int findCircleNum(int[][] isConnected) {
        // build graph
        int n = isConnected.length;
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) map.put(i, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        // dfs traversal
        HashSet<Integer> set = new HashSet<>(); // visited
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                count++;
                set.add(i);
                dfs(map, i, set);
            }
        }
        return count;
    }

    private void dfs(HashMap<Integer, List<Integer>> map, int i , HashSet<Integer> set) {
        for (int next : map.get(i)) {
            if (!set.contains(next)) {
                set.add(next);
                dfs(map, next, set);
            }
        }
    }
}
