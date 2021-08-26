import java.util.*;

public class LeetCode119 {
    /**
     * 1            i = 0, j = []
     * 1, 1         i = 1, j = [1]
     * 1, 1, 1
     * 1, 2, 1:     i = 2, j = [1, 2]
     * 1, 1, 2, 1
     * 1, 3, 3, 1:  i = 3
     */

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        // base case
        if (rowIndex < 0) return result;
        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for (int j = result.size() - 2; j > 0;j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int rowIndex = 5;
        List<Integer> solution = getRow(rowIndex);
        System.out.println(solution);
    }
}
