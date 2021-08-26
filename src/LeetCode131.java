import java.util.*;

public class LeetCode131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        List<String> one = new ArrayList<>();

        int len = s.length();
        boolean[] m = new boolean[len + 1];
        for (int i = 0; i <= len; i++) {
            m[i] = true;
        }

        boolean[][] isPal = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            isPal[i][i] = true;
            for (int j = i - 1; j >= 0; j--) {
                isPal[j][i] = s.charAt(j) == s.charAt(i) && (j + 1 == i || isPal[j + 1][i - 1]);
            }
        }
        search(res, one, s, 0, m, isPal);
        return res;
    }

    public void search(List<List<String>> res, List<String> one, String s, int idx
            , boolean[] m, boolean[][] isPal) {
        int len = s.length();

        if (idx == len) {
            List<String> temp = new ArrayList<>(one);
            res.add(temp);
            return;
        }

        int curSize = res.size();
        for (int length = 0; idx + length < len; length++) {
            if (isPal[idx][idx + length] && m[idx + length + 1]) {
                String str = s.substring(idx, idx + length + 1);
                one.add(str);
                search(res, one, s, idx + length + 1, m, isPal);
                one.remove(one.size() - 1);
            }
        }
        if (curSize == res.size()) {
            m[idx] = false;
        }
    }
}
