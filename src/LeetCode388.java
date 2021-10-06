import java.util.*;

public class LeetCode388 {
    /*
    \n is newline, k numbers \t is kth subdir,
    file contains '.' and end with \n.
     */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        int len = input.length();
        int i = 0;
        int max = 0;
        Stack<int[]> stack = new Stack<>(); // store (fileLen, level)
        while (i < len) {
            if (i < len && input.charAt(i) == '\n') {
                i++;
            }
            int level = 0;
            while (i < len && input.charAt(i) == '\t') {
                i++;
                level++;
            }

            int fileLen = 0;
            boolean isFile = false;
            while (i < len && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.' && i + 1 < len) {
                    isFile = true;
                }
                fileLen++;
                i++;
            }

            while (!stack.isEmpty() && stack.peek()[1] >= level) { // relocate to the prev parent
                stack.pop();

            }
            int[] pushMe = new int[]{(stack.isEmpty() ? 0 : stack.peek()[0] + 1) + fileLen, level};
            stack.push(pushMe);
            if (isFile) {
                max = Math.max(max, pushMe[0]);
            }

        }
        return max;
    }
}
