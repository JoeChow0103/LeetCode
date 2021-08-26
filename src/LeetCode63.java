public class LeetCode63 {
    /*
    fill the matrix, when edge has obstacle, say [0][3] is obstacle
    then right[0][3] and after will be counted 0
    when obstacle is in matrix, then count it 0;
    obstacleGrid[i,j] = obstacleGrid[i-1,j] + obstacleGrid[i,j-1]
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for(int i = 1; i < row; i++){
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }

        for(int j = 1; j < col; j++){
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        }

        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];

            }
        }
        return dp[row - 1][col - 1];
    }
}
