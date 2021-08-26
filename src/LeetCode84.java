import java.util.*;

public class LeetCode84 {
    /*
    solution1: pairs, Time: O(n^3), Space: O(1)
    solution2: reuse the min height, start with the miminum height, Time: O(n^2)
    solution3: divide and conquer:
    possible candidates: left tangles, right tangles, and min tangle.
    Then do it to the left and right each, find the max
    Time: O(nlogn) O(n)
    solution4: stack, when height descends, calculate the area leftward and update max
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i <= len; i++){
            int hei = i < len ? heights[i] : 0;
            while(!stack.isEmpty() && heights[stack.peek()] > hei){
                int top = stack.pop();

                int area = heights[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                max = Math.max(max, area);

            }
            stack.push(i);


        }
        return max;
    }
}
