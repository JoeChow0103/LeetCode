public class LeetCode11 {
    /*
    the area is formed by the lines and limited by the shorter line. A = (right - left) * shorter line.
    The further the lines are, the bigger the area is.
    So use is to use two pointers left and right. Left start from the left end,
    right start from the right end. And maintain a variable max for the area of the current are.
    And update it by moving the left and right towards while the left smaller than left.
    Time: O(N), Space: O(1)
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, curArea);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
