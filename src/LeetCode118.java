import java.util.*;

public class LeetCode118 {
    public static List<List<Integer>> generate(int numRows) {
        final List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
                }
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> solution = generate(numRows);
        System.out.println(solution);
    }
}
