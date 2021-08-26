import java.util.ArrayList;
import java.util.List;

public class LeetCode323 {
    private class V {
        public int label;
        public List<V> nei;
        public boolean visited;

        public V(int label) {
            this.label = label;
            this.visited = false;
            this.nei = new ArrayList<>();
        }

        public void add(V v) {
            this.nei.add(v);
        }

    }
    // solution1 : dfs (v+e)
    public int countComponents(int n, int[][] edges) {
        // build graph;
        List<V> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new V(i));
        }

        for (int[] edge : edges) {
            V prev = list.get(edge[0]);
            V next = list.get(edge[1]);
            prev.add(next);
            next.add(prev);
        }

        int count = 0;
        for (V cur : list) {
            if (!cur.visited) {
                count++;
                dfs(cur);
            }
        }
        return count;
    }

    private void dfs(V cur) {
        cur.visited = true;
        for (V v : cur.nei) {
            if (!v.visited) dfs(v);
        }
    }

    private class UnionFind{
        public int size;
        public int[] idx, sz;

        public UnionFind(int n) {
            size = n;
            idx = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                idx[i] = i;
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

            idx[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            this.size--;
        }

        private int root(int i) {
            int tempI = i;
            while (i != idx[i]) {
                idx[i] = idx[idx[i]];
                i = idx[i];
            }
            idx[tempI] = i;
            return i;
        }
    }

    // solution2 : union find(e*lgv)
    public int countComponentsM(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int v0 = edge[0], v1 = edge[1];
            if (!uf.find(v0, v1)) uf.union(v0, v1);
        }

        return uf.size;
    }
}
