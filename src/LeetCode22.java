import java.util.*;

class LeetCode22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // corner case
        if (n <= 0) {
            return res;
        }
        dfs(n, n, n, new StringBuilder(), res);
        return res;
    }

    private void dfs(int n, int l, int r, StringBuilder sb, List<String> res) {
        // base case
        if (l == 0 && r == 0) {
            res.add(sb.toString());
            return;
        }

        if (l > r) return; // don't forget if left > right, something wrong and must return

        if (l > 0) {
            sb.append('(');
            dfs(n, l - 1, r, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (r > 0 && r > l) { // r parentheses can be add only right > left
            sb.append(')');
            dfs(n, l, r - 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode22 solution = new LeetCode22();
        int n = 3;
        List<String> res = solution.generateParenthesis(n);
        System.out.println(res);
    }
}
