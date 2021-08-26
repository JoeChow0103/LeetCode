public class LeetCode7 {
    /*

     */
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            if (res < Integer.MIN_VALUE) return 0;
            if (res > Integer.MAX_VALUE) return 0;
            x /= 10;
        }
        return (int) res;
    }
}
