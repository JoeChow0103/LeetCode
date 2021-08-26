import java.util.*;

public class LeetCode261 {
    /*
    use the property of tree: # of node = # of edge + 1, if connected graph.
    if find(i, j) means before i and j has already find a parent, then cycle.
    Time: O(logn), Space: O(1), time is the height of the tree

    solution2: graph bfs check cycle
     */
    class UnionFind {
        public int size;
        public int[] ids, sz;

        public UnionFind(int n) {
            size = n;
            ids = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
                sz[i] = 1;
            }
        }

        public boolean find(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int rootP = root(p), rootQ = root(q);
            if (sz[rootP] < sz[rootQ]) {
                int temp = rootP;
                rootP = rootQ;
                rootQ = temp;
            }

            ids[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            this.size--;
        }

        private int root(int i) {
            int tempI = i;
            while (i != ids[i]) {
                ids[i] = ids[ids[i]];
                i = ids[i];
            }
            ids[tempI] = i;
            return i;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges == null) return false;
        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            int p = e[0], q = e[1];
            if (uf.find(p, q)) {
                return false;
            } else {
                uf.union(p, q);
            }
        }

        return uf.size == 1;
    }

//    public boolean validTree(int n, int[][] edges) {
//        if(edges.length + 1 != n){
//            return false;
//        }
//
//        Map<Integer, Set<Integer>> graph = new HashMap<>();
//        for(int i = 0; i < n; i++){
//            graph.put(i, new HashSet<>());
//        }
//
//        for(int[] edge : edges){
//            int a = edge[0];
//            int b = edge[1];
//            graph.get(a).add(b);
//            graph.get(b).add(a);
//        }
//
//        int[] visited = new int[n];
//        for(int i = 0; i < n; i++){
//            if(hasCycle(i, graph, visited)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean hasCycle(int cur, Map<Integer, Set<Integer>> graph, int[] visited){
//        if(visited[cur] == 2){
//            return false;
//        }
//        if(visited[cur] == 1){
//            return true;
//        }
//
//        visited[cur] = 1;
//        for(int nei : graph.get(cur)){
//            //graph.get(cur).remove(nei);
//            graph.get(nei).remove(cur);
//            if(hasCycle(nei, graph, visited)){
//                return true;
//            }
//        }
//        visited[cur] = 2;
//        return false;
//    }
}
