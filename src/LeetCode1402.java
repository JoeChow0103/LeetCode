import java.util.Arrays;

public class LeetCode1402 {
    /*
    satisfaction = [-1, -8, 0, 5, -9]
    dp: dp[i] = sum starting from i
    Time Complexity: O(nlogn)
     */
    public int maxSatisfaction(int[] satisfaction) {
        // corner case;
        Arrays.sort(satisfaction);

        int[] dp = new int[satisfaction.length];
        dp[0] = getSum(satisfaction, 0);

        for (int i = 1; i < satisfaction.length; i++) {
            dp[i] = getSum(satisfaction, i);
            if (dp[i] <= dp[i - 1]) {
                return dp[i - 1];
            }
        }
        return 0;
    }

    private int getSum(int[] cur, int start) {
        int sum = 0;
        int time = 1;
        for (int i = start; i < cur.length; i++, time++) {
            sum = sum + (cur[i] * time);
        }

        return sum;
    }

    public static void main(String[] args) {
        LeetCode1402 solution = new LeetCode1402();
        int[] satisfaction = {-1, -8, 0, 5, -9};
        int result = solution.maxSatisfaction(satisfaction);
        System.out.println(result);
    }
}
