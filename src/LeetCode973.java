import java.util.*;

public class LeetCode973 {
    /*
    solution 1: sort. Time: NlogN, Space: N
    solution 2: quickSelect. Time: N in average, N^2 in worst case, Space: N
     */
//    public int[][] kClosest(int[][] points, int K) {
//        // solution 1
//        int len = points.length;
//        int[] dists = new int[len];
//        for (int i = 0; i < len; i++) {
//            dists[i] = dist(points[i]);
//        }
//
//        Arrays.sort(dists);
//        int distK = dists[K - 1];
//
//        int[][] ans = new int[K][2];
//        int t = 0;
//        for (int[] point : points) {
//            if (dist(point) <= distK) {
//                ans[t++] = point;
//            }
//        }
//        return ans;
//    }
//
//    private int dist(int[] point) {
//        return point[0] * point[0] + point[1] * point[1];
//    }
    public int[][] kClosest(int[][] points, int K) {
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void quickSelect(int[][] points, int left, int right, int K) {
        if (left >= right) {
            return;
        }

        int index = partition(points, left, right);
        if (index == K - 1) {
            return;
        }

        if (index > K) {
            quickSelect(points, left, index - 1, K);
        } else {
            quickSelect(points, index + 1, right, K);
        }
    }

    private int partition(int[][] points, int left, int right) {
        int pivot = right;
        int pivotVal = distSqr(points, right);
        right--;

        while (left <= right) {
            if (distSqr(points, left) <= pivotVal) {
                left++;
            } else if (distSqr(points, right) > pivotVal) {
                right--;
            } else {
                swap(points, left, right);
            }
        }
        swap(points, left, pivot);
        return left;
    }

    private int distSqr(int[][] points, int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
