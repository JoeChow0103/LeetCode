import java.util.*;

public class LeetCode93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        addIP(res, new StringBuilder(), 0, s, 0);

        return res;
    }

    public void addIP(List<String> res, StringBuilder path, int index, String s, int num) {
        if (num == 4) {
            if (index == s.length()) {
                path.setLength(path.length() - 1);
                res.add(path.toString());
            }
            return;
        }

        int length = path.length();
        for (int i = 1; i <= 3; i++) {
            if (index + i > s.length()) {
                break;
            }

            int val = Integer.valueOf(s.substring(index, index + i));
            if (val <= 255) {
                path.append(val + ".");
                addIP(res, path, index + i, s, num + 1);
                path.setLength(length);
            }
            // if 0 start,  shouldn't go ahead
            if (val == 0) {
                break;
            }
        }
    }
}
