import java.util.*;

public class LeetCode329 {
    public int longestIncreasingPath(int[][] matrix) {
        // c.c.
        int row = matrix.length, col = matrix[0].length;
        int dp[][] = new int[row][col];

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max, search(matrix, Integer.MIN_VALUE, i, j, dp));
            }
        }
        return max;
    }

    private int search(int[][] m, int curNum, int i, int j, int[][] dp) {
        int row = m.length, col = m[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || m[i][j] <= curNum) return 0;

        if (dp[i][j] != 0) return dp[i][j];
        int max = 0;
        max = Math.max(max, search(m, m[i][j], i - 1, j, dp));
        max = Math.max(max, search(m, m[i][j], i + 1, j, dp));
        max = Math.max(max, search(m, m[i][j], i, j - 1, dp));
        max = Math.max(max, search(m, m[i][j], i, j + 1, dp));
        dp[i][j] = max + 1;
        return max + 1;
    }
}
