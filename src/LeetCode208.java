import java.util.*;

public class LeetCode208 {
}
class Trie {
    /*
    clarify: only a-z or ASCII, 26: array, 256: hashmap
    recommendation system, autocomplete
    hashmap should be avoid when the limited ASCII, since cannot
    use the continuous memory using hashing.
    But when ASCII is complex, then use hashmap
     */

    class Node{
        char val;
        Node[] children;
        boolean isWord;
        public Node(char val){
            this.val = val;
            children = new Node[26];
            isWord = false;
        }
    }


    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node('\0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for(char c : word.toCharArray()){
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new Node(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for(char c : word.toCharArray()){
            if(cur.children[c - 'a'] == null){
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(char c : prefix.toCharArray()){
            if(cur.children[c - 'a'] == null){
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }
}