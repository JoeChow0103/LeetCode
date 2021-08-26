import java.util.*;

public class LeetCode305 {
    /*
    UnionFind:
    1. compress the path, update the parent to the cur node, so won't
    traverse back to the oldest parent. Decrease the tree height
    2. decrease the tree size, union the small tree to big tree, reduce
    the cost

    find&union(p, neighbour)
    Time: O(V*E), Space: O(n), time is the height of the tree
     */
    private final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private class UnionFind{
        // fields
        public int[] parent;    // parent of this node
        public int[] size;      // size of group when this node as root
        public int numberOfIslands;    // number of islands
        public int row;
        public int col;

        // constructor
        public UnionFind (int row, int col) {
            this.row = row;
            this.col = col;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            numberOfIslands = 0;
            for (int i = 0; i < len; i++) {
                parent[i] = -1;     // initial this node is not island
                size[i] = 1;        // initial size of the group is himself
            }
        }

        public boolean find(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public void union(int p, int q) {
            int rootP = getRoot(p);
            int rootQ = getRoot(q);

            // union small group to larger group
            if (size[p] < size[q]) {
                union(q, p);
            } else {
                // q <= p, merge q to p
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];

                // reduce number of isolated groups
                numberOfIslands--;
            }
        }

        private int getRoot(int p) {
            int cur = p;
            while(parent[cur] != cur) {
                parent[cur] = parent[parent[cur]];
                cur = parent[cur];
            }
            parent[p] = cur;    // faster pointer for next time access
            return cur;
        }

        public int index (int i, int j) {
            return i * col + j;
        }

        public void addIsland(int p) {
            if (parent[p] == -1) {
                parent[p] = p;
                numberOfIslands++;
            }
        }

        public boolean isIsland(int p) {
            return parent[p] != -1;
        }

    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (positions == null || positions[0].length == 0) {
            return res;
        }

        UnionFind uf = new UnionFind(m, n);

        for (int k = 0; k < positions.length; k++) {
            int i = positions[k][0];
            int j = positions[k][1];
            int p = uf.index(i, j);
            uf.addIsland(p);
            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                int q = uf.index(ii, jj);
                if (ii >= 0 && ii < m && jj >= 0 && jj < n
                        && uf.isIsland(q)
                        && !uf.find(p, q)) {
                    uf.union(p, q);
                }
            }

            res.add(uf.numberOfIslands);
        }

        return res;
    }

    public static void main(String[] args) {
        LeetCode305 solution = new LeetCode305();
        int m = 3, n = 3;
        int[][] positions = {{0,1},{0,0},{1,2},{1,2}};
        List<Integer> res = solution.numIslands2(m, n, positions);
        System.out.println(res);
    }
}
