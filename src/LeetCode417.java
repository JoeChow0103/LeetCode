import java.util.*;

public class LeetCode417 {
    /**
     * Pacific ~   ~   ~   ~   ~
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *           *   *   *   *   * Atlantic
     * Graph:
     * 1. Branches: 4 directions;
     * 2. Graph: matrix[i][j] -> i * col + j;
     * 3. Kind: similar to Shortest path
     * 4. Search: from sides to points
     * 5. BFS/DFS: BFS: dealing with the questions: many to one; DFS: need a dummy root which is difficult
     * solution:
     * 1. from sides to points to see if the point can reach the ocean;
     * 2. two pass: one is from Pacific, the other is from Atlantic
     * 3. The first pass is to value points which can reach Pacific,
     * the second is to the to check points which can reach Atlantic,
     * then find the points which can reach both oceans.
     * @param matrix
     * @return
     */
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int row = matrix.length, col = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        // Pacific: put the start point in queue, and set true as visited
        loadAllPacificStartPoints(matrix, queue, pacific);
        bfs(matrix, queue, pacific, atlantic, res);

        // Atlantic
        loadAllAtlanticStartPoints(matrix, queue, atlantic);
        bfs(matrix, queue, atlantic, pacific, res);

//        System.out.println("atlantic");
//        for (int i = 0; i < row; i++) {
//            System.out.println(Arrays.toString(atlantic[i]));
//        }
//
//        System.out.println("pacific");
//        for (int i = 0; i < row; i++) {
//            System.out.println(Arrays.toString(pacific[i]));
//        }

        return res;
    }

    private void bfs(int[][] matrix, Queue<Integer> queue,
                     boolean[][] self, boolean[][] another,
                     List<List<Integer>> res) {
        int row = matrix.length, col = matrix[0].length;
        while (!queue.isEmpty()) {
            // this time don't need size, which represent the level
            // but we still can use while (size-- > 0), which is a property of bfs
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                // we hope to check the point of self and another all true, then put in result
                if (another[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row
                            && jj >= 0 && jj < col
                            && matrix[ii][jj] >= matrix[i][j] // important
                            && !self[ii][jj]) {
                        queue.offer(ii * col + jj);
                        self[ii][jj] = true;
                    }
                }
            }
        }
    }

    private void loadAllAtlanticStartPoints(int[][] matrix,
                                            Queue<Integer> queue,
                                            boolean[][] atlantic) {
        int row = matrix.length, col = matrix[0].length;
        // right side [i][col - 1]
        for (int i = 0; i < row; i++) {
            queue.offer(i * col + col - 1);
            atlantic[i][col - 1] = true;
        }

        // bottom side [row - 1][j]
        for (int j = 0; j < col - 1; j++) {
            queue.offer((row - 1) * col + j);
            atlantic[row - 1][j] = true;
        }
    }

    private void loadAllPacificStartPoints(int[][] matrix,
                                           Queue<Integer> queue,
                                           boolean[][] pacific) {
        int row = matrix.length, col = matrix[0].length;
        // left side [i][0]
        for (int i = 0; i < row; i++) {
            queue.offer(i * col + 0);
            pacific[i][0] = true;
        }

        // top side [0][j]
        for (int j = 1; j < col; j++) {
            queue.offer(0 * col + j);
            pacific[0][j] = true;
        }
    }

    public static void main(String[] args) {
        LeetCode417 solution = new LeetCode417();
        int[][] matrix = {{1, 2, 2, 3, 5},
                          {3, 2, 3, 4, 4},
                          {2, 4, 5, 3, 1},
                          {6, 7, 1, 4, 5},
                          {5, 1, 1, 2, 4}};
        List<List<Integer>> res = solution.pacificAtlantic(matrix);
        System.out.println(res);
    }
}
