import java.util.*;

class TrieNode {
    public char ch;
    public TrieNode[] nexts = new TrieNode[26];
    public boolean isLeaf = false;
    public boolean isFound = false;

    public TrieNode(char c) {
        ch = c;
    }
}

public class LeetCode212 {
    /*
    clarify: if the same letter will be used more than once, like person, personal
    if not, then use isLeaf; otherwise, need to maintain isFound.
    Trie + dfs all possible path
    dfs: start from one node has 4 branches, when get out of the recursion(base case)
    like a inverted indexes.
     */
    public List<String> findWords(char[][] board, String[] words) {
        //c.c
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || board[0] == null
        || words == null || words.length == 0) return res;

        TrieNode root = new TrieNode('\0');
        // build Trie for words
        for (String s : words) {
            TrieNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (cur.nexts[idx] == null) {
                    cur.nexts[idx] = new TrieNode(s.charAt(i));
                }
                cur = cur.nexts[idx];
            }
            cur.isLeaf = true;
        }

        // search
        int row = board.length, col = board[0].length;
        boolean visited[][] = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                search(res, board, visited, i, j, new StringBuilder(), root);
            }
        }
        return res;
    }

    private void search(List<String> res, char[][] board, boolean[][] visited, int i, int j,
                        StringBuilder curStr, TrieNode root) {
        int row = board.length, col = board[0].length;
        // base case
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] ||
        root.nexts[board[i][j] - 'a'] == null) {
            return;
        }

        TrieNode next = root.nexts[board[i][j] - 'a'];
        visited[i][j] = true;
        curStr.append(board[i][j]);
        if (next.isLeaf) {
            res.add(curStr.toString());
            next.isLeaf = false;
        }

        search(res, board, visited, i - 1, j, curStr, next);
        search(res, board, visited, i + 1, j, curStr, next);
        search(res, board, visited, i, j + 1, curStr, next);
        search(res, board, visited, i, j - 1, curStr, next);

        // backtracing
        visited[i][j] = false;
        curStr.deleteCharAt(curStr.length() - 1);
    }

}
