import java.util.ArrayList;
import java.util.List;

public class LeetCode241 {
    /*
    Recursively divide the expression by operator, then calculate the left and right expression.
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        boolean singalNum = true;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                singalNum = false;
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, expression.length()));

                List<Integer> path = combine(left, right, c);
                res.addAll(path);
            }
        }
        if (singalNum) res.add(Integer.valueOf(expression));
        return res;
    }

    private List<Integer> combine(List<Integer> left, List<Integer> right, char optr) {
        List<Integer> res = new ArrayList<>();
        for (int l : left) {
            for (int r : right) {
                int result = 0;
                if (optr == '+') result = l + r;
                if (optr == '-') result = l - r;
                if (optr == '*') result = l * r;

                res.add(result);
            }
        }
        return res;
    }
}
