import java.util.*;

public class LeetCode694 {
    /**
     * 1 1 0 0 0
     * 1 1 0 0 0
     * 0 0 0 1 1
     * 0 0 0 1 1
     * DIRECTIONS: up, down, right, left
     * visited
     * need to consider the distinct island. In other word,
     * we need to record the shape of the island.
     * We can use set to record, and use shape as key.
     * Shapes can be determined by directions with char.
     * Also use dfs to check every possible direction until it fails.
     * The success case of dfs is when the value of a node is 1;
     * The failure case of dfs is when the value of a node is 0 and hit the board;
     * Still have one question left: the shape may be "srdl" and "srdl", the question
     * is what if the shape is actually different but the strings are the same. Thus,
     * we need record the turning point to avoid this situation.
     */
    public int numDistinctIslands(int[][] grid) {
        // c.c.
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        HashSet<String> set = new HashSet<>();
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <col; j++) {
                if (grid[i][j] != 0) {
                    // we use string to record the shape, char as element, so use stringbuilder
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 's'); // init with 's'
                    set.add(sb.toString());
                }
            }
        }
        System.out.println(set);
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, char dir) {
        int row = grid.length, col = grid[0].length;
        // base case
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) {
            return;
        }

        // general case
        // check visited: this time just inplace the value from 1 to 0
        grid[i][j] = 0;
        sb.append(dir);
        dfs(grid, i, j + 1, sb, 'r');
        dfs(grid, i + 1, j, sb, 'd');
        dfs(grid, i, j - 1, sb, 'l');
        dfs(grid, i - 1, j, sb, 'u');
        sb.append('t');
    }

    public static void main(String[] args) {
        LeetCode694 solution = new LeetCode694();
        int[][] positions = {{1,1,0,0,0},
                            {1,1,0,0,0},
                            {0,0,0,1,1},
                            {0,0,0,1,1}};
        int res = solution.numDistinctIslands(positions);
        System.out.println(res);
    }
}
