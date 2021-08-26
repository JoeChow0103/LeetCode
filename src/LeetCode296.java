import java.util.*;

class LeetCode296 {
    /**
     * the meaning of wall is to avoid tricky math solution, which is pointless in algo test
     * this is similar to LC317 but without walls
     * Time Complextiy: O(m^2*n^2)
     * Consider:
     * 1.there is no zeros but only ones
     * 2.all nodes' distance will be update, so no check when update min, ones have been visited
     */
//    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//    public int minTotalDistance(int[][] grid) {
//        // corner case
//        if (grid == null || grid.length == 0
//                || grid[0] == null || grid[0].length == 0) {
//            return -1;
//        }
//        int row = grid.length, col = grid[0].length;
//        int[][] cost = new int[row][col];
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                if (grid[i][j] == 1) {
//                    bfs(grid, i, j, cost);
//                }
//            }
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                min = Math.min(cost[i][j], min);
//
//            }
//        }
////        for (int i = 0; i < row; i++) {
////            System.out.println(Arrays.toString(cost[i]));
////        }
//
//        return min;
//    }
//
//    private void bfs(int[][] grid, int rowIndex, int colIndex, int[][] cost) {
//        int row = grid.length, col = grid[0].length;
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(rowIndex * col + colIndex);
//        boolean[][] visited = new boolean[row][col];
//        visited[rowIndex][colIndex] = true;
//        // cost[rowIndex][colIndex] = 0;
//        int minLen = 1;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                int cur = queue.poll();
//                int i = cur / col, j = cur % col;
//                for (int[] dir : DIRECTIONS) {
//                    int ii = i + dir[0];
//                    int jj = j + dir[1];
//                    if (ii >= 0 && ii < row
//                            && jj >= 0 && jj < col
//                            && !visited[ii][jj]) {
//                        queue.offer(ii * col + jj);
//                        visited[ii][jj] = true;
//                        cost[ii][jj] += minLen;
//                    }
//                }
//            }
//            minLen++;
//        }
//    }

    /**
     * solution2: find the medians of rows and cols
     * which coordinate a point getting the minDistance
     * use selection algo to find medians
     *
     */
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size() / 2);
        int col = cols.get(cols.size() / 2);
        return minDistance1D(rows, row) + minDistance1D(cols, col);
    }

    private int minDistance1D(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) { // caution: the order of iteration
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }


    public static void main(String[] args) {
        LeetCode296 solution = new LeetCode296();
        int[][] grid = {{1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}};
        int[][] matrix = {{1, 1},{1, 1}};
        int min = solution.minTotalDistance(grid);
        System.out.println(min);
    }
}
