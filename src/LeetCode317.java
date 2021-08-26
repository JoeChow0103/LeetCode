import java.util.*;

public class LeetCode317 {
    /**
     * build a house on an empty land which reaches all buildings in the shortest
     * amount of distance.
     * Each 0 marks an empty land which you can pass by freely.
     * Each 1 marks a building which you cannot pass through.
     * Each 2 marks an obstacle which you cannot pass through.
     * From ones to zeros, call k times bfs
     * 1. brute force: Time Complexity O(k*m*n), Space Complexity O(m*n)
     * 2. consider the zeros are surrounded by twos, then those zeros won't be visited forever
     * , which we can set those zeros to twos
     * {{1, 1, 1, 0, 1},
     *  {0, 0, 2, 2, 2},
     *  {0, 0, 2, 0, 1}} case I: no zeros can reach all one
     *  {{1, 1, 1, 0, 1},
     *   {0, 0, 2, 2, 2},
     *   {0, 0, 2, 0, 0}} case II: some zeros can reach one
     * @param grid
     * @return
     */
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int shortestDistance(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;
        int[][] cost = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, cost);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // post-processing the zeros in cost
                // if (cost[i][j] == 0) continue; WRONG!
                if (grid[i][j] == 0 && cost[i][j] != 0) {
                    min = Math.min(min, cost[i][j]);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(cost[i]));
        }

        if (min == Integer.MAX_VALUE) { // case 1
            return -1;
        } else {
            return min;
        }
    }

    private void bfs(int[][] grid, int rowIndex, int colIndex, int[][] cost) {
        int row = grid.length, col = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(rowIndex * col + colIndex);
        boolean[][] visited = new boolean[row][col];
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
                            && jj >=0 && jj < col
                            && grid[ii][jj] == 0
                            && !visited[ii][jj]) {
                        queue.offer(ii * col + jj);
                        visited[ii][jj] = true;
                        cost[ii][jj] += minLen; // accumulate sum
                    }
                }
            }
            minLen++;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    grid[i][j] = 2; // case II: set surrounded zeros to twos
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode317 solution = new LeetCode317();
        int[][] grid = {{1, 0, 2, 0, 1},
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0}};
        int[][] matrix = {{1, 1}, {1, 1}};
        int min = solution.shortestDistance(matrix);
        System.out.println(min);
    }
}
