import java.util.*;

class LeetCode79 {
    /**
     * word = ABCCED
     * board:
     * A B C E
     * S F C S
     * A D E E
     * 1. from the starter char, there are 4 directions to go. {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
     * 2. because every time just go to one direction to find if it is the next char in the board, use dfs
     * 3. dfs: base case 1, the index equals to the word.length, which means it goes to the last char of the word;
     * 4. dfs: base case 2, the index out of the bounds, visited, not the char, return false;
     * 4. dfs: general case, check the char of the board is the current char in word, then move to next step;
     * 5. dfs: this is a matrix or a graph which is no direction, so need to record if the node is visited;
     * 6, dfs(char[][] board, char[] wordChar, int curIdx, int i, int j, boolean[][] visited) return boolean;
     * 7. this we may need extra data structures
     */
    public boolean exist(char[][] board, String word) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        char[] wordChar = word.toCharArray();
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, wordChar, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] wordChar, int curIdx, int i, int j, boolean[][] visited) {
        // base case
        if (curIdx == wordChar.length) {
            return true;
        }

        int row = board.length, col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] || board[i][j] != wordChar[curIdx]) {
            return false;
        }

        visited[i][j] = true;
        curIdx++;
        boolean res = dfs(board, wordChar, curIdx, i + 1, j, visited) ||
                dfs(board, wordChar, curIdx, i - 1, j, visited) ||
                dfs(board, wordChar, curIdx, i, j + 1, visited) ||
                dfs(board, wordChar, curIdx, i, j - 1, visited);
        visited[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        LeetCode79 solution = new LeetCode79();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean res = solution.exist(board, word);
        System.out.println(res);
    }
}
