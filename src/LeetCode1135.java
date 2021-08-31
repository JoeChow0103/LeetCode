import java.util.Arrays;

public class LeetCode1135 {
    class UnionFind {
       private int[] weights;
       private int[] parents;

       public void union(int a, int b) {
           int rootA = find(a);
           int rootB = find(b);
           if (rootA == rootB) return;

           if (this.weights[rootA] > this.weights[rootB]) {
               // In case rootA is having more weight
               // 1. Make rootA as the parent of rootB
               // 2. Increment the weight of rootA by rootB's weight
               this.parents[rootB] = rootA;
               this.weights[rootA] += this.weights[rootB];
           } else {
               // Otherwise
               // 1. Make rootB as the parent of rootA
               // 2. Increment the weight of rootB by rootA's weight
               this.parents[rootA] = rootB;
               this.weights[rootB] += this.weights[rootA];
           }
       }

       public int find(int a) {
           while (a != this.parents[a]) {
               this.parents[a] = this.parents[this.parents[a]];
               a = this.parents[a];
           }
           return a;
       }

       public boolean isSameRoot(int a, int b) {
           return find(a) == find(b);
       }

       public UnionFind(int n) {
           this.parents = new int[n + 1];
           this.weights = new int[n + 1];
           for (int i = 1; i < n + 1; i++) {
               this.parents[i] = i;
               this.weights[i] = i;
           }
       }

    }
    public int minimumCost(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        // sort by weight
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int total = 0;
        int cost = 0;
        for (int i = 0; i < connections.length; i++) {
            int a = connections[i][0];
            int b = connections[i][1];
            // don't add edge from a to b if already connected;
            if (uf.isSameRoot(a, b)) continue;
            // if a and b are not connected, take union
            uf.union(a, b);
            //increment cost
            cost += connections[i][2];
            // increment number of edges
            total++;
        }
        // if all N nodes are connected, the MST will have a total n-1 edges
        if (total == n - 1) {
            return cost;
        } else {
            return -1;
        }
    }
}
