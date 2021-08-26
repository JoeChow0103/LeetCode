import java.util.*;

public class LeetCode126M {
    /*
    output: any one of shortest path
     */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();

        HashSet<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }
        if (!wordSet.contains(endWord)) {
            return res;
        }

        wordSet.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> graph = new HashMap<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                char[] cc = cur.toCharArray();
                for (int i = 0; i < cc.length; i++) {
                    char temp = cc[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cc[i] = c;
                        String next = String.valueOf(cc);
                        if (c != temp && wordSet.contains(next)) {
                            graph.put(next, cur);

                            if (next.equals(endWord)) {
                                String curWord = endWord;
                                while (curWord != null) {// while cur is not next of beginWord, do...
                                    res.add(0, curWord);
                                    curWord = graph.get(curWord); //TODO: if has neighbors
                                }
                                return res;
                            }
                            queue.offer(next);
                            wordSet.remove(next);
                        }
                    }
                    cc[i] = temp;
                }
            }
        }
        return res;
    }
}
