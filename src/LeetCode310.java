import java.util.*;

public class LeetCode310 {
    /*
    start from the leafNode, when traverse a leafNode, remove the leafNode from its neighbor
    then the tree become a new tree without the current leafNode,
    then find the new leafNode and do the same thing, until no leafNode
     */
    private class TreeNode {
        public HashSet<Integer> nei;
        public int label;

        public TreeNode(int label) {
            this.label = label;
            nei = new HashSet<>();
        }

        public void addNei(Integer label) {
            nei.add(label);
        }

        public void removeNei(Integer label) {
            nei.remove(label);
        }

        public boolean isLeave() {
            // if it's leaf, the only neighbor is its parent
            return nei.size() == 1;
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        // build tree
        int nodePool = n;
        TreeNode vs[] = new TreeNode[n];
        for (int i = 0; i < n; i++) vs[i] = new TreeNode(i);
        for (int[] arr : edges) { // O(E)
            vs[arr[0]].addNei(arr[1]);
            vs[arr[1]].addNei(arr[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (vs[i].isLeave()) {
                queue.add(i);
                nodePool--;
            }
        }

        while (nodePool > 0) { // O(V + E)
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int i : vs[cur].nei) {
                    vs[i].removeNei(cur);
                    if (vs[i].isLeave()) {  // add when the current Node become a leafNode
                        queue.add(i);
                        nodePool--;
                    }
                }
            }
        }

        for (int i : queue) res.add(i);
        return res;
    }
}
