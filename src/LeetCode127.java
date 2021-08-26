import java.util.*;

public class LeetCode127 {
    /**
     * Dictionary -> HashSet: Capacity, avoid for each, no duplicates
     * 1. Branches: Graph
     * 2. Graph: vectors: words, edges: word transferred to other word, cost = 1
     * 3. Kind: similar to Shortest Path
     * 4. Search:
     * 5. Time: O(26^word.length * wordList.size()), Space: O(wordList.size())
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // corner case
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }

        HashSet<String> dictionary = new HashSet<>();
        for (String word : wordList) {
            dictionary.add(word);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dictionary.remove(beginWord);
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nextWords = convert(cur, dictionary);
                for (String next : nextWords) {
                    if (dictionary.contains(next)) {
                        if (next.equals(endWord)) { // caution: compare strings, use equals
                            return minLen + 1;
                        }
                        queue.offer(next);
                        dictionary.remove(next);
                    }
                }
            }
            minLen++;
        }
        return 0;
    }

    private List<String> convert(String cur, HashSet<String> dictionary) {// Time: 26^cur.length
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String str = String.valueOf(chars);
                if (dictionary.contains(str)) {
                    res.add(str);
                }
            }
            chars[i] = temp;
        }
        return res;
    }


    public static void main(String[] args) {
        LeetCode127 solution = new LeetCode127();
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        int res = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
