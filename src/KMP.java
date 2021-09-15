import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static int[] build(String P) {
        if (P == null) return null;

        int m = P.length();
        char[] p = P.toCharArray();
        int[] next = new int[m];
        next[0] = 0;
        for (int i = 1, j = 0; i < m; i++) {
            // fail then jump until a match or j == 0
            while (j > 0 && p[i] != p[j]) {
                j = next[j - 1];
            }
            if (p[i] == p[j]) j++;
            next[i] = j;
        }
        return next;
    }

    public static List<Integer> match(String S, String P) {
        int n = S.length(), m = P.length();
        char[] s = S.toCharArray();
        char[] p = P.toCharArray();

        List<Integer> res = new ArrayList<>();
        // build next table
        int[] next = build(P);

        for (int i = 0, j = 0; i < n; i++) {
            // fail then jump until a match or j == 0
            while (j > 0 && s[i] != p[j]) {
                j = next[j - 1];
            }
            // match then check next pair
            if (s[i] == p[j]) j++;
            // find a full match
            if (j == m) {
                res.add(i - m + 1);
                // jump as it fail
                j = next[j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = build("leetcodeleet");
        for (int n : res ) System.out.println(n);
    }
}
