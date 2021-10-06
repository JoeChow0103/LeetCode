import java.util.*;

public class LeetCode695 {
    public int maxAreaOfIsland(int[][] grid) {
        // c.c.
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int res = 0;
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(!visited[i][j]) res = Math.max(res, dfs(grid, i, j, visited));
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int row = grid.length, col = grid[0].length;
        // base case
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;
        return dfs(grid, i + 1, j, visited) +
                dfs(grid, i - 1, j, visited) +
                dfs(grid, i, j + 1, visited) +
                dfs(grid, i, j - 1, visited) + 1;
    }

    public static void main(String[] args) {
        LeetCode695 solution = new LeetCode695();
//        int[][] grid = {{0}};
//        System.out.println(grid[0].length);
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int res = solution.maxAreaOfIsland(grid);
        System.out.println(res);
    }
}
