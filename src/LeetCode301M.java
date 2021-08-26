import java.util.*;

public class LeetCode301M {
    /*
    in process: delta < 0 => rmR++, when delta < 0, rmR++
    end: delta > 0 => rmL=delta, when go to the end, delta > 0, rml - delta
    here delta = rmL
     */
    private List<Integer> calMinParentheseToRemove(String s) {
        int rmL = 0, rmR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                rmL++;
            } else if (c == ')') {
                if (rmL > 0) {
                    rmL--;
                } else { // rmL == 0
                    rmR++;
                }
            }
        }
        return Arrays.asList(rmL, rmR);
    }
}
