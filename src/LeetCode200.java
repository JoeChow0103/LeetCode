import java.util.*;

public class LeetCode200 {
    // solution1: bdfs
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numIslands(char[][] grid) {
        // c.c.
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        int row = grid.length, col = grid[0].length;

        // base case
        if (i < 0 || i >=row || j < 0 || j >= col || visited[i][j] || grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            dfs(grid, ii, jj, visited);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}};
        LeetCode200 solution = new LeetCode200();
        int res = solution.numIslands(grid);
        System.out.println(res);
    }
}
