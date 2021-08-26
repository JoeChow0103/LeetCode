import java.util.*;

public class LeetCode286 {
    /**
     * -1: wall; 0: gate; INF: empty room
     * Shortest path.
     * Graph:
     * 1. It has branches.
     * 2. Graph (V, E, directions, weights): V = matrix [i][j]
     * 3. What kind of questions in graph: Graph -> Shortest Path -> BFS
     * 4. Search: begins, the nums of search
     * 5. BFS / DFS
     * Time Complexity:
     * v Brute: O(K) * O(V+E), V: O(m*n), E: O(4*m*n) -> O(k*m*n)
     * @param rooms
     */
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        // corner case
        if (rooms == null || rooms.length == 0
                || rooms[0] == null || rooms[0].length == 0) {
            // throw exception;
            return;
        }
        int row = rooms.length, col = rooms[0].length;
        // i, j -> i * col + j: i = int / col, j = int % col
        // consider int is more than Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        addAllZeros(rooms, queue); // add all begins
        int minLen = 1; // demo: the next step, the surround will be update to 1
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row
                            && jj >=0 && jj < col
                            && rooms[ii][jj] == Integer.MAX_VALUE) {
                        queue.offer(ii * col + jj);
                        rooms[ii][jj] = minLen;
                    }
                }
            }
            minLen++;
        }
    }

    private void addAllZeros(int[][] rooms, Queue<Integer> queue) {
        int row = rooms.length, col = rooms[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode286 solution = new LeetCode286();
        int inf = Integer.MAX_VALUE;
        int[][] rooms = {{inf, -1, 0, inf},
                {inf, inf, inf, -1},
                {inf, -1, inf, -1},
                {0, -1, inf, inf}};
        solution.wallsAndGates(rooms);
        int row = rooms.length, col = rooms[0].length;
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(rooms[i]));
        }
    }
}
