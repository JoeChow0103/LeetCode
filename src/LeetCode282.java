import java.util.*;

public class LeetCode282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, num, 0, 0, new StringBuilder(), target);
        return res;
    }

    private void dfs(List<String> res, String num, long last, long curVal,
                     StringBuilder sb, int target) {
        int len = num.length();
        if (len == 0 && curVal == target) {
            res.add(sb.toString());
            return;
        }

        if (len == 0) return;

        int lenSb = sb.length();
        long val = 0;
        for (int i = 0; i < len; i++) {
            val = 10 * val + num.charAt(i) - '0';

            if (sb.length() != 0) {
                dfs(res, num.substring(i + 1), val, curVal + val,
                        sb.append("+").append(val), target);
                sb.setLength(lenSb);

                dfs(res, num.substring(i + 1), -val, curVal - val,
                        sb.append("-").append(val), target);
                sb.setLength(lenSb);

                dfs (res, num.substring(i + 1), last * val, (curVal - last) + last * val,
                        sb.append("*").append(val), target);
                sb.setLength(lenSb);
            } else {
                dfs(res, num.substring(i + 1), val, curVal + val,
                        sb.append(val), target);
                sb.setLength(lenSb);
            }
            if (val == 0) break;
        }
    }
}
