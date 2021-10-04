import java.util.Arrays;
import java.util.Stack;

public class LeetCode503 {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
//        for (int i = len * 2 - 1; i >= 0; i--) {
//            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % len]) {
//                stack.pop();
//            }
//            // if nums[stack.peek()] > nums[i % len]
//            res[i % len] = stack.empty() ? -1 : nums[stack.peek()];
//            stack.push(i % len);
//        }

        for (int i = 0; i < len * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]) {
                res[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return res;
    }
}
