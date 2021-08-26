import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AmicableNumbers {
    public List<List<Integer>> findAmicablePairs(int n) {
        List<List<Integer>> res = new ArrayList<>();
        // c.c.
        if (n <= 2) return res;

        HashMap<Integer, Integer> amicableMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            if (amicableMap.containsKey(i) && sumOfDivs(i) == amicableMap.get(i)) {
                res.add(new ArrayList<>(Arrays.asList(amicableMap.get(i), i)));
            } else {
                amicableMap.put(sumOfDivs(i), i);
            }
        }
        return res;
    }

    public int sumOfDivs(int n) {
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (n % i != i) {
                    sum += n / i;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        AmicableNumbers solution = new AmicableNumbers();
        System.out.println(solution.findAmicablePairs(500));
    }
}
