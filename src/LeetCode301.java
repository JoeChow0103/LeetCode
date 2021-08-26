import java.util.*;

public class LeetCode301 {
    /*
    "()())()" has duplicated )) => hashset result deduplicate
    all possible solution
    dfs(res, cc, index, numL, numR, open, sb)
    numL&&numR is the number of parentheses to remove
    delta evaluate the path match scenario, meet open ++, close --
    base case: success and fail case
        success, idx == len && rmL == 0 && rmR == 0 && delta(valid) == 0 ")("
        fail, idx > len || rmL < 0 || rmR < 0 || delta < 0
    set visited? no
    three branches: open, close, other
    Time Complexity: 2^n

     */
    public List<String> removeInvalidParentheses(String s) {
        // c.c.
        if (s == null) return new ArrayList<>();

        HashSet<String> set = new HashSet<>();
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')'){
                if (rmL == 0) rmR++;
                else rmL--;
            }
        }
        dfs(set, s, 0, rmL, rmR, 0, new StringBuilder());
        return new ArrayList<>(set);
    }

    private void dfs(HashSet<String> set, String s, int idx, int rmL, int rmR, int delta, StringBuilder sb) {
        // base case
        if (idx == s.length() && rmL == 0 && rmR == 0 && delta == 0) { // success case
            set.add(sb.toString());
            return;
        }
        if (idx >= s.length() || rmL < 0 || rmR < 0 || delta < 0) { // fail case
            return;
        }

        char c = s.charAt(idx);
        if (c == '(') {
            // remove
            dfs(set, s, idx + 1, rmL - 1, rmR, delta, sb);
            // keep
            sb.append('(');
            dfs(set, s, idx + 1, rmL, rmR, delta + 1, sb);
            sb.setLength(sb.length() - 1);
        } else if (c == ')') {
            // remove
            dfs(set, s, idx + 1, rmL, rmR - 1, delta, sb);
            // keep
            sb.append(')');
            dfs(set, s, idx + 1, rmL, rmR, delta - 1, sb);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(c);
            dfs(set, s, idx + 1, rmL, rmR, delta, sb);
            sb.setLength(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        LeetCode301 solution = new LeetCode301();
        String s = "()())()";
        System.out.println(solution.removeInvalidParentheses(s));
    }

}
