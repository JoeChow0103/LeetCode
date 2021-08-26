import java.util.ArrayList;
import java.util.List;

public class LeetCode320 {
    public List<String> generateAbbreviations(String word) {
        // c.c.
        List<String> res = new ArrayList<>();
        dfs(word, res, 0, 0, new StringBuilder());
        return res;
    }

    private void dfs(String word, List<String> res, int prevCount, int index, StringBuilder path) {

        int len = word.length();
        if (index == len) {
            if (prevCount > 0) {
                int lenP = path.length();
                path.append(prevCount);
                res.add(path.toString());
                path.setLength(lenP);
            } else {
                res.add(path.toString());
            }
            return;
        }

        // regard this char as a number, and count1
        // don't use the preCount
        dfs(word, res, prevCount + 1, index + 1, path);

        // use the preCount
        int lenP = path.length();
        if (prevCount > 0) {
            path.append(prevCount);
        }
        path.append(word.charAt(index));
        dfs(word, res, 0, index + 1, path); // prevCount has been used, so start 0 again
        path.setLength(lenP);
    }
}
