public class LeetCode37 {
    public void solveSudoku(char[][] board) {
        solveBoard(board);
    }

    // branches: set which number, and check isValid, if not, then backtrace
    private boolean solveBoard(char[][] board) {
        final int row = board.length;
        final int col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[i][j] = '.'; // backtracing
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            final char rowChar = board[i][col];
            final char colChar = board[row][i];
            final char blockChar = board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3];
            if (rowChar != '.' && rowChar == c) return false; // check duplicate in rowIndex;
            if (colChar != '.' && colChar == c) return false; // check duplicate in colIndex;
            if (blockChar != '.' && blockChar == c) return false; // check duplicate in 3*3 blocks;
        }
        return true;
    }
}
