import java.util.*;

public class LeetCode130 {
    /*
    remember, the Os on boarder and those Os connect with Os on boarder cannot be modified
    Thus, start from boarder find the Os on boarder first, then find the connected Os.
    Finally, traverse all element modified the canBeChanged Os
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int row = board.length, col = board[0].length;
        Queue<Integer[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) { // 'O' on l&r boarder
            if (board[i][0] == 'O') {
                board[i][0] = 'Y';
                Integer ij[] = {i, 0};
                queue.offer(ij);
            }
            if (board[i][col - 1] == 'O') {
                board[i][col - 1] = 'Y';
                Integer ij[] = {i, col - 1};
                queue.offer(ij);
            }
        }

        for (int j = 0; j < col; j++) { // 'O' on u&d boarder
            if (board[0][j] == 'O') {
                board[0][j] = 'Y';
                Integer ij[] = {0, j};
                queue.offer(ij);
            }
            if (board[row - 1][j] == 'O') {
                board[row - 1][j] = 'Y';
                Integer ij[] = {row - 1, j};
                queue.offer(ij);
            }
        }

        while (!queue.isEmpty()) {
            Integer[] cur = queue.poll();

            int i = cur[0], j = cur[1];
            if (i - 1 >= 0 && board[i - 1][j] == 'O') {
                board[i - 1][j] = 'Y';
                Integer ij[] = {i - 1, j};
                queue.add(ij);
            }

            if (j + 1 < col && board[i][j + 1] == 'O') {
                board[i][j + 1] = 'Y';
                Integer ij[] = {i, j + 1};
                queue.add(ij);
            }

            if (i + 1 < row && board[i + 1][j] == 'O') {
                board[i + 1][j] = 'Y';
                Integer ij[] = {i + 1, j};
                queue.add(ij);
            }

            if (j - 1 >= 0 && board[i][j - 1] == 'O') {
                board[i][j - 1] = 'Y';
                Integer ij[] = {i, j - 1};
                queue.add(ij);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'Y') board[i][j] = 'O';
            }
        }
    }
}
