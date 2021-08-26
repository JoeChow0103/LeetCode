import java.util.*;

public class LeetCode51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // c.c.
        if (n <= 0) return result;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        dfs(n, 0, board, result);
        return result;
    }

    private void dfs(int n, int colIdx, char[][] board, List<List<String>> res) {
        if (colIdx == n) {
            res.add(covertMatrix(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(board, i, colIdx)) { // for current colIdx
                board[i][colIdx] = 'Q';
                dfs(n, colIdx + 1, board, res);
                board[i][colIdx] = '.';
            }

        }
    }

    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if ((board[i][j] == 'Q') && (x == i || Math.abs(x - i) == Math.abs(y - j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> covertMatrix(char[][] board) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }

        return list;
    }

}
