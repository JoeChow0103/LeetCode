public class LeetCode1746 {
    /*
    one operation: num[i] = num[i]*num[i]
    nums = [2, -1, -4, -3]
    withOps: 0  4  3  17 14
    without: 0  2  1  0  0
    max_s_f: 0  4  4  17 17
    withOperation + curr;
    withOutOperation + (curr * curr);

    withOperation = max(withoutOperation + (curr * curr), withOperation + curr);
    withOutOperation = kandane's algorithm

    max sum = subarray
     */
    public int maxSumAfterOperation(int[] nums) {
        int withOp = 0;
        int withOutOp = 0;
        int res = 0; // max_so_far

        for (int n : nums) {
            res = Math.max(withOp + n, res);
            if (withOp + n > res) {// continue add
                res = withOp + n;
            }
            if (withOutOp + (n*n) > res) {// if operate
                res = withOutOp + (n*n);
            }

            withOp = Math.max(withOutOp + (n*n), withOp + n);
            withOutOp = Math.max(withOutOp + n, 0);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode1746 solution = new LeetCode1746();
        int[] nums = {2, -1, -4, -3};
        int res = solution.maxSumAfterOperation(nums);
        System.out.println(res);
    }
}
