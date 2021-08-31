import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCode642 {
}


class AutocompleteSystem {
    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children;
        List<String> top; // save top 3

        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
            top = new ArrayList<>();
        }
    }

    private final int size = 3;
    private final TrieNode root;
    private final HashMap<String, Integer> map;
    private TrieNode cur;
    private StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode('\0');
        map = new HashMap<>();
        cur = root;
        sb = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
            addWord(sentences[i]);
        }
    }

    private void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.get(c) == null) {
                node.children.put(c, new TrieNode(c));
            }
            node = node.children.get(c);

            List<String> topList = node.top;
            if (!topList.contains(word)) {
                topList.add(word); // record the word from root to cur ndoe
            }

            // sort the top list
            topList.sort((c1, c2) -> {
                if (map.get(c1) .equals(map.get(c2))) {
                    return c1.compareTo(c2);
                } else {
                    return map.get(c2) - map.get(c1);
                }
            });

            // if top size larger then 3, then remvoe
            while (topList.size() > size) {
                topList.remove(topList.size() - 1);
            }
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if (c == '#') {
            // finish the input, save the current input to Trie and HashMap
            String word = sb.toString();
            // put to hashMap
            map.put(word, map.getOrDefault(word, 0) + 1);
            // update Trie
            addWord(word);

            // finish input, post-process to back to original state
            cur = root;
            sb = new StringBuilder();
            return new ArrayList<>();
        }

        sb.append(c);
        if (cur != null) {
            cur = cur.children.get(c);
        }

        return cur == null ? new ArrayList<>() : cur.top;
    }
}
