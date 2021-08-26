import java.util.*;

public class LeetCode140 {
    /**
     * s = "catsanddog", wordDict = ["cat", "cats","and","sand","dog"]
     * cat|sanddog -> cat|sand|dog
     * 1. cut the s from each position, and check if the right and left part exist in the dict.
     * 2. if the one part exist in the dict, we will cut the other part, then check if r&l part exist in the dict
     * 3. we need a List<String> to store word the checked & already in dict.
     * 4. so we do it with dfs
     * 5. direction: left -> right, branches: if the left || right part exists, then cut(branches)
     * 6. pruning: s = "aaaaa", dict = ["a","aa","aaa","aaaa",...], List<List<string>> mem, pointless, use too much memory
     * @param s
     * @param wordDict
     * @return
     *
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }

        int len = s.length();
        HashSet<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }

        dfs(s, dict, 0, res, new StringBuilder());
        return res;
    }

    private void dfs(String s, HashSet<String> dict, int idx, List<String> res, StringBuilder path) {
        int len = s.length();
        // base case
        if (idx == len) {
            res.add(path.toString());
            return;
        }

        for (int i = idx; i < len; i++) {
            String str = s.substring(idx, i + 1);
            if (dict.contains(str)) {
                int lenPath = path.length();
                if (lenPath == 0) {
                    path.append(str);
                } else {
                    path.append(" " + str);
                }
                dfs(s, dict, i + 1, res, path);
                path.setLength(lenPath);
            }
        }
    }


    public static void main(String[] args) {
        LeetCode140 solution = new LeetCode140();
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<String>(Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"}));
        List<String> res = solution.wordBreak(s, wordDict);
        System.out.println(res);
    }
}
