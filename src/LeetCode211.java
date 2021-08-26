public class LeetCode211 {
}
class WordDictionary {
    /*
    clarify: only a-z or ASCII, 26: array, 256: hashmap
    recommendation system, autocomplete
    hashmap should be avoid when the limited ASCII, since cannot
    use the continuous memory using hashing.
    But when ASCII is complex, then use hashmap

    When write more use ArrayList/HashSet, write: O(1), read: O(n)
    when read more use Hashset+enum, write: O(2^k), read: O(1), if contains "."
    abc -> ab. a.c .bc a.. ...
    when the same use Trie, write: O(k), read: O(n)
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
    public WordDictionary() {
        root = new Node('\0');
    }

    public void addWord(String word) {
        Node cur = root;
        for(char c : word.toCharArray()){
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new Node(c);
            }
            cur = cur.children[c - 'a'];

        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node cur = root;
        return helper(word, 0, cur);
    }

    private boolean helper(String word, int index, Node cur){
        if(cur == null){
            return false;
        }
        if(index == word.length()){
            return cur.isWord; // important
        }

        char c = word.charAt(index);
        if(c != '.'){
            cur = cur.children[c - 'a'];
            return helper(word, index + 1, cur);
        }else{
            for(Node child : cur.children){
                if(helper(word, index + 1, child)){
                    return true;
                }
            }
            return false;
        }
    }
}