import java.util.*;

public class LeetCode120 {
    /**
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // Method5: dp reuse Space: O(n) ?????????????
        // c.c
        if (triangle == null || triangle.size() == 0) return 0;
        int size = triangle.size();
        int[] minDepth = new int[size + 1];

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                minDepth[j] = Math.min(minDepth[j], minDepth[j + 1] + triangle.get(i).get(j));
            }
        }
        System.out.println(Arrays.toString(minDepth));
        return minDepth[0];
    }

//    public int minimumTotal(List<List<Integer>> triangle) {
//        // Method4: dp inplace Space: O(1)
//        int n = triangle.size();
//        for (int i = n - 2; i >= 0; i--) {
//            for (int j = 0; j <= i; j++) {
//                triangle.get(i).set(j, triangle.get(i).get(j)
//                        + Math.min(triangle.get(i + 1).get(j + 1), triangle.get(i + 1).get(j)));
//            }
//        }
//        return triangle.get(0).get(0);
//    }

//    public int minimumTotal(List<List<Integer>> triangle) {
//        // Method3: dp n^2
//        int n = triangle.size();
//        int[][] dp = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            dp[n - 1][i] = triangle.get(n - 1).get(i);
//        }
//        for (int i = n - 2; i >=0; i--) {
//            for (int j = 0; j <= i; j++) { // j <= i important details
//                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
//            }
//        }
////        System.out.println(Arrays.deepToString(dp));
//        return dp[0][0];
//    }

//    int min = Integer.MAX_VALUE;
//    public int minimumTotal(List<List<Integer>> triangle) {
//        // Method1: recursion: top down
//        int sum = 0;
//        minTotalHelper(0, 0, sum, triangle);
//        return min;
//    }
//
//    private void minTotalHelper(int i, int j, int sum, List<List<Integer>> triangle) {
//        // base case
//        int n = triangle.size();
//        if (i == n) {
//            if (sum < min) {
//                min = sum;
//            }
//            return;
//        }
//        minTotalHelper(i + 1, j, sum + triangle.get(i).get(j), triangle);
//        minTotalHelper(i + 1, j + 1, sum + triangle.get(i).get(j), triangle);
//    }

//    int[][] dp;
//    public int minimumTotal(List<List<Integer>> triangle) {
//        // Method2: recursion + dp：bottom up
//        dp = new int[triangle.size()][triangle.size()];
//        for (int[] i : dp) Arrays.fill(i, Integer.MAX_VALUE);
//        return minTotalHelper(0, 0, triangle);
//    }
//    private int minTotalHelper(int i, int j, List<List<Integer>> triangle) {
//        int level = triangle.size();
//        // base case
//        if (i == level) {
//            return 0;
//        }
//        // check if the node has called
//        if (dp[i][j] != Integer.MAX_VALUE) {
//            return dp[i][j];
//        }
//        int left = minTotalHelper(i + 1, j, triangle);
//        int right = minTotalHelper(i + 1, j + 1, triangle);
//        // restore the minimum value of sum to dp
//        dp[i][j] = triangle.get(i).get(j) + Math.min(left, right);
//        return dp[i][j];
//    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        LeetCode120 solution = new LeetCode120();
        int sum = solution.minimumTotal(triangle);
        System.out.println(sum);
    }
}
