public class LeetCode265 {
    /*
    (i,j)
    |
    min
    |
    (i-1,j)
    dp[i][j] is the min cost of choose color j from 0 to i row
    init: dp[0][j] = matrix[0][j]
    dp[i][j] = matrix[i][j] + min(dp[i-1][j])
     */

    public int minCostII(int[][] costs) { // O(n*k*k)
        int n = costs.length, k = costs[0].length;
//        for (int house = 1; house < n; house++) {
//            for (int color = 0; color < k; color++) {
//                int min = Integer.MAX_VALUE;
//                for (int prevColor = 0; prevColor < k; prevColor++) {// improve by find 1st&2nd min
//                    if (color == prevColor) continue;
//                    min = Math.min(min, costs[house - 1][prevColor]);
//                }
//                costs[house][color] += min;
//            }
//        }
        for (int house = 1; house < n; house++) {
            // find 1st and 2nd minColor in prev row
            int minColor = -1, secondMinColor = -1;
            for (int color = 0; color < k; color++) {
                int cost = costs[house - 1][color];
                if (minColor == -1 || cost < costs[house - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = color;
                } else if (secondMinColor == -1 || cost < costs[house - 1][secondMinColor]) {
                    secondMinColor = color;
                }
            }
            // calculate the new cosst for the current row
            for (int color = 0; color < k; color++) {
                if (color == minColor) {
                    costs[house][color] += costs[house - 1][secondMinColor];
                } else {
                    costs[house][color] += costs[house - 1][minColor];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }

//    public int minCostII(int[][] costs) {
//        int n = costs.length, k = costs[0].length;
//        if (k == 1) return n == 1 ? costs[0][0] : 0;
//
//        int[][] dp = new int[n + 1][k + 1];
//        int[] val = new int[k];
//
//        for (int i = 1; i <= n; i++) {
//            int rowIndex = i - 1;
//            for (int j = 0; j < k; j++) {
//                val[j] = costs[rowIndex][j] + dp[i-1][j];
//            }
//            getNotVal(dp, i, val);
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < k; i++) {
//            min = Math.min(min, dp[n][i]);
//        }
//        return min;
//    }
//
//    private void getNotVal(int[][] dp, int n, int[] val) {
//        int len = val.length;
//        int leftMin = val[0], rightMin = val[len - 1];
//        for (int i = 1; i < len; i++) {
//            dp[n][i] = leftMin;
//            leftMin = Math.min(leftMin, val[i]);
//        }
//        for (int i = len - 2; i > 0; i--) {
//            dp[n][i] = Math.min(dp[n][i], rightMin);
//            rightMin = Math.min(rightMin, val[i]);
//        }
//        dp[n][0] = rightMin;
//    }
}
