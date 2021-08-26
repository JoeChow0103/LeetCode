import java.util.*;

public class LeetCode207 {
    /*
    graph + topology
    Time: O(v+e), Space: O(v+e)
     */
    enum Status {
        INITIAL,
        PROCESSING,
        DONE;
    }

    class V {
        public int label;
        public List<Integer> nexts;
        public Status status;

        public V (int label) {
            this.label = label;
            this.nexts = new ArrayList<>();
            status = Status.INITIAL;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            if (isCycled(array[i], array)) {
                return false;
            }
        }

        return true;
    }

    public boolean isCycled( V source, V[] array) {
        if (source.status == Status.DONE) return false; // pruning
        if (source.status == Status.PROCESSING) return true; // contains cycle

        source.status = Status.PROCESSING;
        for (int i : source.nexts) {
            if (isCycled(array[i], array)) {
                return true;
            }
        }
        source.status = Status.DONE;
        return false;
    }
}
