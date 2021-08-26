import java.util.*;

public class LeetCode210 {
    /*
    graph + topology
    Time: O(v+e), Space: O(v+e)
     */

    /*
    graph + topology
    Time: O(v+e), Space: O(v+e)
     */
    enum Status {
        INITIAL,
        PROCESSING,
        DONE;
    }

    private class V {
        public int label;
        public List<Integer> nexts;
        public Status status;

        public V (int label) {
            this.label = label;
            this.nexts = new ArrayList<>();
            status = Status.INITIAL;
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<V> path = new ArrayList<>();
        // c.c
        // construct graph
        V array[] = new V[numCourses];
        for (int i = 0; i < numCourses; i++) {
            array[i] = new V(i);
        }

        int row = prerequisites.length;
        for (int i = 0; i < row; i++) {
            int prev = prerequisites[i][1], next = prerequisites[i][0];
            array[prev].nexts.add(next);
        }

        for (int i = 0; i < numCourses; i++) {
            if (isCycled(array[i], array, path)) {
                return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = path.get(i).label;
        }
        return res;
    }

    private boolean isCycled(V source, V[] array, List<V> path) {
        if (source.status == Status.DONE) return false; // pruning
        if (source.status == Status.PROCESSING) return true; // contains cycle

        source.status = Status.PROCESSING;
        for (int i : source.nexts) {
            if (isCycled(array[i], array, path)) {
                return true;
            }
        }
        source.status = Status.DONE;
        path.add(0, source);
        return false;
    }
//    private int curLab;
//
//    private class Vertex {
//        public int val;
//        public List<Integer> nexts;
//        public boolean visited = false;
//
//        public Vertex (int i) {
//            val = i;
//            this.nexts = new ArrayList<>();
//        }
//    }
//
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        Vertex array[] = new Vertex[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            array[i] = new Vertex(i);
//        }
//
//        int row = prerequisites.length;
//        for (int i = 0; i < row; i++) {
//            int prev = prerequisites[i][1], next = prerequisites[i][0];
//            array[prev].nexts.add(next);
//        }
//
//        int res[] = new int[numCourses];
//        curLab = numCourses - 1;
//        for (int i = 0; i < numCourses; i++) {
//            if (!topo(res, array[i], array, new HashSet<>())) {
//                return new int[0];
//            }
//        }
//        return res;
//    }
//
//    private boolean topo(int[] res, Vertex source, Vertex[] array, HashSet<Integer> set) {
//        if (set.contains(source.val)) return false;
//        if (source.visited) return true; // pruning
//
//        source.visited = true;
//        set.add(source.val);
//        for (int i : source.nexts) {
//            if (!topo(res, array[i], array, set)) {
//                return false;
//            }
//        }
//
//        set.remove(source.val);
//        res[curLab--] = source.val;
//        return true;
//    }
}
