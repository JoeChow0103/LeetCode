public class LeetCode348 {
}

class TicTacToe {
    private final int n;
    private final int[][] board;
    private final int[] horizontal;
    private final int[] vertical;
    private int diagonal;
    private int antiDiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.n = n;
        board = new int[n][n];
        horizontal = new int[n];
        vertical = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        // player 1: add 1
        // player 2: minus 1;
        if (player == 1) {
            horizontal[row]++;
            vertical[col]++;
            if (row == col) {
                diagonal++;
            }
            if (board.length - 1 - row == col) {
                antiDiagonal++;
            }
        }

        if (player == 2) {
            horizontal[row]--;
            vertical[col]--;
            if (row == col) {
                diagonal--;
            }
            if (board.length - 1 - row == col) {
                antiDiagonal--;
            }
        }

        // check win
        if (horizontal[row] == n || vertical[col] == n || diagonal == n || antiDiagonal == n) {
            return 1;
        } else if (horizontal[row] == -n || vertical[col] == -n || diagonal == -n || antiDiagonal == -n) {
            return 2;
        } else {
            return 0;
        }
    }

}