public class LeetCode50 {
    public double myPow(double x, int n) {
        if (n < 0) return 1.0 / (x*myPow(x, -(n + 1)));
        if (n == 0) return 1.0;
        double d = myPow(x, n / 2);
        if (n % 2 == 0) {
            return d * d;
        } else {
            return d * d * x;
        }
    }
}
