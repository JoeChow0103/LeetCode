import java.util.*;

public class LeetCode695 {
    /**
     * similar to 694
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0; // think about the [[0]], the result should be 0
        // c.c.
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return res;
        }

        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 0) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 's');
                    int size = sb.length(); // remember the starter should be counted
                    if (res < size) {
                        res = size;
                    }
                }
            }
        }

        return res;
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, char dir) {
        int row = grid.length, col = grid[0].length;
        // base case
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) {
            return;
        }

        // general case
        grid[i][j] = 0;
        sb.append(dir);
        dfs(grid, i, j + 1, sb, 'r');
        dfs(grid, i + 1, j, sb, 'd');
        dfs(grid, i, j - 1, sb, 'l');
        dfs(grid, i - 1, j, sb, 'u');
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
