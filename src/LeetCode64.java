public class LeetCode64 {
    /*
    solution1: for each num, two paths right and down, and find the mim sum
    cost(i, j) = grid[i][j] + min(cost(i-1,j), cost(i,j-1))
    Time: O(2^(m+n)), Space: (m+n)
    solution2: 2d dp reuse the grid
    Time: O(m*n), Space(1)
     */
    public int minPathSum(int[][] grid) {
        final int row = grid.length;
        final int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0 && j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i != 0 && j != 0) {
                    grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[row - 1][col - 1];
    }

//    public int calculate(int[][] grid, int i, int j) {
//        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
//        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
//        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
//    }
//    public int minPathSum(int[][] grid) {
//        return calculate(grid, 0, 0);
//    }
}
