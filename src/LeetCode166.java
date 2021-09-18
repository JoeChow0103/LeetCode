import java.util.HashMap;
import java.util.HashSet;

public class LeetCode166 {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder fraction = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        Long dividend = Math.abs(Long.valueOf(numerator));
        Long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(dividend / divisor);
        long remainder = dividend % divisor;
        if (remainder == 0) return fraction.toString();

        fraction.append(".");
        HashMap<Long, Integer> fractionMap = new HashMap<>();
        while (remainder != 0) {
            if (fractionMap.containsKey(remainder)) {
                fraction.insert(fractionMap.get(remainder), "(");
                fraction.append(")");
                break;
            }
            fractionMap.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(remainder / divisor);
            remainder %= divisor;
        }
        return fraction.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(-1 ,-2147483648));
    }
}
