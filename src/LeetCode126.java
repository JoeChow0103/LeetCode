import java.util.*;

public class LeetCode126 {
    /*
    bfs  + dfs / topo
    bfs construct the graph;
    dfs record the path
    Time: O(26^word.length * wordList.size() + V+E); Space(V+E)
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();

        HashSet<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }

        Queue<String> queue = new LinkedList<>();
        HashMap<String, List<String>> graph = new HashMap<>();
        queue.offer(beginWord);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Set<String> visitedThisLev = new HashSet<>();
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                char cc[] = cur.toCharArray();
                for (int i = 0; i < cc.length; i++) {
                    char temp = cc[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cc[i] = c;
                        String str = String.valueOf(cc);
                        if (c != temp && wordSet.contains(str)) {
                            if (str.equals(endWord)) flag = true;

                            if (!visitedThisLev.contains(str)) {
                                List<String> one = new ArrayList<>();
                                one.add(cur);
                                graph.put(str, one);
                                queue.offer(str);
                                visitedThisLev.add(str);
                            } else {
                                List<String> one = graph.get(str);
                                one.add(cur);
                                graph.put(str, one);
                            }
                        }
                    }
                    // backtracing
                    cc[i] = temp;
                }
            }
            wordSet.removeAll(visitedThisLev);

            if (flag) {
                List<String> one = new LinkedList<>();
                one.add(endWord);
                search(res, one, endWord, beginWord, graph);
                return res;
            }
        }
        return res;
    }

    private static void search(List<List<String>> res, List<String> one, String cur,
                        String end, HashMap<String, List<String>> graph) {
        if (cur.equals(end)) {
            List<String> copy = new LinkedList<String>(one);
            res.add(copy);
            return;
        }

        List<String> next = graph.get(cur);
        for (String n : next) {
            one.add(0, n);
            search(res, one, n, end, graph);
            one.remove(0);
        }
    }

    public static void main(String[] args) {
        String b = "hit";
        String e = "cog";
        String[] words = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(words);
        findLadders(b, e, wordList);
    }
}
