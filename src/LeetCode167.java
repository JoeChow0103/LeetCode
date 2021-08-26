public class LeetCode167 {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int start = 0, end = len - 1;
        while (start <= end) {
            long sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[] {start + 1, end + 1};
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return null;
    }
}
