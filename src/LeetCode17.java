import java.util.*;

public class LeetCode17 {
    private static final HashMap<Character, char[]> phone = new HashMap<>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        // c.c.
        if (digits == null || digits.length() == 0) return res;

        char[] nums = digits.toCharArray();
        dfs(res, new StringBuilder(), 0, digits);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int idx, String digits) {
        // base case
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c : phone.get(digits.charAt(idx))) {
            sb.append(c);
            dfs(res, sb, idx + 1, digits);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode17 solution = new LeetCode17();
        String digits = "23";
        List<String> res = solution.letterCombinations(digits);
        System.out.println(res);
    }
}
