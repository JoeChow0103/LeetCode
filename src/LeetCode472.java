import java.util.*;

public class LeetCode472 {
    /*
    trie store the words list, can call search.
    may search the cat, but can still search afterward as catanddog
     */
    class TrieNode {
        private char ch;
        private TrieNode[] next;
        private boolean isWord;
        TrieNode(char ch) {
            this.ch = ch;
            this.next = new TrieNode[26];
            this.isWord = false;
        }
    }

    TrieNode root = new TrieNode('\0');

    private void insertWord(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (curr.next[ch - 'a'] == null) {
                curr.next[ch - 'a'] = new TrieNode(ch);
            }
            curr = curr.next[ch - 'a'];
        }
        curr.isWord = true;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        for (String word : words) {
            insertWord(word);
        }

        // Now the trie is built, check each word in the words
        for (String word : words) {
            if (dfs(word, 0, 0)) {
                res.add(word);
            }
        }

        return res;
    }

    // Return whether [idx, word.length() - 1] is in the trie
    private boolean dfs(String word, int idx, int used) {
        if (idx == word.length()) {
            return used > 1; //Need at least twice
        }

        TrieNode curr = root;
        for (int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.next[ch - 'a'] == null) {
                return false;
            }
            curr = curr.next[ch - 'a'];

            if(curr.isWord) {
                if(dfs(word, i + 1, used + 1)){
                    return true;
                }
            }
            // if (curr.isWord && dfs(word, i + 1, used + 1)) {
            //     return true;
            // }
        }
        return false;
    }
}
