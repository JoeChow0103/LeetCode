public class LeetCode174 {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length, col = dungeon[0].length;
        int[][] m = new int[row + 1][col + 1];
        // initial
        for (int i = 0; i < row; i++) {
            m[i][col] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < col; j++) {
            m[row][j] = Integer.MAX_VALUE;
        }

        // dp[i][j] is the minimum cost from right-bottom to (i,j)
        // dp[i][j] = max(0, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j],
        // nums[i][j] > nums[i+1][j] && nums[i][j] > nums[i+1][j]
        // right: dp[i+1][j]; down: dp[i][j+1]
        // dp[i][j]
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i == row - 1 && j == col - 1) {
                    m[i][j] = Math.max(0, -dungeon[i][j]);
                } else {
                    m[i][j] = Math.max(0, Math.min(m[i+1][j],
                            m[i][j+1]) - dungeon[i][j]);
                }
            }
        }
        return m[0][0] + 1;
    }
}
