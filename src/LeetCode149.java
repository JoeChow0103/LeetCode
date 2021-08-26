public class LeetCode149 {
    /*
    enumeration, math to check three points are on one line.
    don't use slope, for slope is not accurate
    no repeat, no miss. if there are duplicates points
     */
    public int maxPoints(int[][] points) {
        // Arrays.sort(points);
        int res = 0;
        int len = points.length;
        for (int i = 0; i < len; i++) {
            int duplicate = 1;
            for (int j = i + 1; j < len; j++) {
                int count = 0;
                long x1 = points[i][0];
                long y1 = points[i][1];
                long x2 = points[j][0];
                long y2 = points[j][1];
//                if (x1 == x2 && y1 == y2) {
//                    duplicate++;
//                    continue;
//                }
                for (int[] point : points) {
                    int x3 = point[0];
                    int y3 = point[1];
                    if (x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3 == 0) {
                        count++;
                    }
                }
                res = Math.max(res, count);
            }
//            res = Math.max(res, duplicate);
        }
        return res;
    }
}
