import java.util.*;

public class LeetCode488 {
    HashMap<Character, Integer> charCount = new HashMap<>();
    int ans = -1;
    public int findMinStep(String board, String hand) {
        for (char i : hand.toCharArray()) {
            charCount.put(i, charCount.getOrDefault(i, 0) + 1);
        }
        dfs(board, 0);
        return ans;
    }

    private void dfs(String s, int count){
        if (s.length() == 0) {
            if (ans == -1) ans = count;
            else ans = Math.min(count, ans);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            int t = i + 1;
            // find the endpoint for substring of one char: aaaa
            while (t < s.length() && s.charAt(t) == s.charAt(i)) {
                t++;
            }

            int seqLen = t - i;
            int cc = Math.max(charCount.getOrDefault(s.charAt(i), 0), 0);

            if (seqLen + cc >= 3) {
                int numMoves = seqLen < 3 ? 3 - seqLen : 0;
                charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) - numMoves);
                String newString = s.substring(0, i) + s.substring(t, s.length());
                dfs(newString, count + numMoves);
                charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + numMoves);
            }
            i = t - 1;
        }
    }
}
