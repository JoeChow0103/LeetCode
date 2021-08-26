import java.util.*;

class LeetCode139 {
    /**
     * s = "leetcode", wordDict = ["leet", "code"]
     * split s from the first index,
     * l eetcode, le etcode, lee tcode, leet code,...
     * l e etcode, l ee tcode, l eet code, ...
     * 1. The number of chars we have in s is how many branches we have at the first level. Let's say n branch on first level
     * 2. The second level, we have n -1 branches. And so on.
     * 3. for each level, s is segment to several parts. We need to check if the part exist on the wordDict. We can use HashSet to check with O(1) time.
     * 4. If the first part exist on the word, then we segment the leftover. Otherwise, we return false.
     * 5. When the  index goes to the end, we can return true which means the case is fullfilled.
     * 6. Else if the index goes out of bound, we can return false.
     * 7. We use dfs to do it.
     * 8. pruning:
     * s = "abcxxxxx",wordDict = ["abc", "a", "bc",...]
     * a|bc|xxxxx, abc|xxxxx: 0 -> 1 ->3, 0 -> 3 duplicates
     * 9. before pruning: worst case: only last char doesn't work. s -> m, dict -> n, O(n^m), height is m
     * 10. after pruning: O (m^3) because the dic.contains(word) is m in worst case.
     *
     * solution 2: dp.
     * [0,i) -> read [0,j) is true or false when find [j,i) is in the dict
     * boolean dp[i]: [0 : i) -> parse True/False
     * dp[0] = true; dp[0] cover the case when we first check the piece of string is dictionary contains.
     * dp[i] = dp[i] || (dp[j] && (dp[j - i])), s[j, i] belongs to dict && o <= j < i
     * draw a line
     * @param s
     * @param wordDict
     * @return
     */
//    public boolean wordBreak(String s, List<String> wordDict) {
//        // corner case
//        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
//            return false;
//        }
//
//        HashSet<String> set = new HashSet<>();
//        int len = s.length();
//        for (String word : wordDict) {
//            set.add(word);
//        }
//
//        Boolean[] mem = new Boolean[len + 1];
//        return dfs(s, 0, set, mem);
//    }
//
//    private boolean dfs(String s, int idx, HashSet<String> dict, Boolean[] mem) {
//        int len = s.length();
//        // base case
//        if (idx == len) {
//            return true;
//        }
//
//        // O(1)
//        if (mem[idx] != null) {
//            return mem[idx];
//        }
//
//        for (int i = idx; i < len; i++) { // O(m)
//            String str = s.substring(idx, i + 1); // [idx, i+1)
//            if (dict.contains(str)) {
//                if (dfs(s, i + 1, dict, mem)) { // i has been processed, so i + 1
//                    mem[idx] = true;
//                    return true;
//                }
//            }
//        }
//        // return false; if don't pruning
//        mem[idx] = false;
//        return mem[idx];
//    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0) return true;
        if(wordDict == null) return true;
        int len = s.length();
        Set<String> dict = new HashSet<>();
        for(String str : wordDict){
            dict.add(str);
        }
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){ // dp[i+1]
            for(int j = 0; j <= i; j++){
                if(dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        LeetCode139 solution = new LeetCode139();
        String s = "leetcode";
        List<String> wordDict = new ArrayList<String>(Arrays.asList(new String[]{"leet", "code"}));
        boolean res = solution.wordBreak(s, wordDict);
        System.out.println(res);
    }
}
