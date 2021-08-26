public class LeetCode829 {
    /*
    consecutive numbers sum
    15 = 7+8 = 4+5+6 = 1+2+3+4+5
    N = (x+1) + (x+2) + (x+3) + ...+(x+k) = kx + (1+k)*k/2
    x = N/k - (k+1)/2
    satisfy: x >= 0, x is Natural Number
    k <= (2N+1/4)^0.5 - .5
     */
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        int upper_limit = (int) (Math.sqrt(2 * n + 0.25) - 0.5);
        for (int k = 1; k <= upper_limit; k++) {
            if ((n - k*(k+1)/2) % k == 0) {
                count++;
            }
        }
        return count;
    }
}
