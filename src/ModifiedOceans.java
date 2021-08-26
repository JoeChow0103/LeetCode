import java.util.*;

class ModifiedOceans {
    /**
     * find the point which has shortest path to oceans
     * vertex and sides of matrix are not consider; -1 is obstacle
     */
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int pacificAtlanticShortPath(int[][] matrix) {
        // cc
        // corner case
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return -1;
        }

        int row = matrix.length, col = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        int[][] res = new int[row][col];

        // Pacific: put the start point in queue, and set true as visited
        loadAllPacificStartPoints(matrix, queue, pacific);
        bfs(matrix, queue, pacific, atlantic, res);

        // Atlantic
        loadAllAtlanticStartPoints(matrix, queue, atlantic);
        bfs(matrix, queue, atlantic, pacific, res);

        int min = Integer.MAX_VALUE;
        int[][] points = new int[row][col];
        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    min = Math.min(min, res[i][j]);
                    points[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(points[i]));
        }

        return min;
    }

    private void bfs(int[][] matrix, Queue<Integer> queue,
                     boolean[][] self, boolean[][] other,
                     int[][] res) {
        int row = matrix.length, col = matrix[0].length;
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row
                            && jj >= 0 && jj < col
                            && matrix[ii][jj] >= matrix[i][j]// important
                            && !self[ii][jj]
                            && matrix[ii][jj] != -1) {
                        queue.offer(ii * col + jj);
                        self[ii][jj] = true;
                        if (other[ii][jj]) {
                            res[ii][jj] += minLen;
                        } else {
                            res[ii][jj] = minLen;
                        }
                    }
                }
            }
            minLen++;
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
        ModifiedOceans solution = new ModifiedOceans();
        int[][] matrix = {{1, 2, 2, 3, 5},
                          {3, 2, 3, 4, 4},
                          {2, 4, 5, 3, 1},
                          {6, 7, 1, 4, 5},
                          {5, 1, 1, 2, 4}};
        int res = solution.pacificAtlanticShortPath(matrix);
        System.out.println(res);
    }
}
