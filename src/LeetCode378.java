import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode378 {
    /*
    use a priority queue to store the number, and poll the number k times, then it's the answer
    the problem is don't know the left or down number which one is smaller. Then use bfs all offer to queue, then
    automatically poll the right order.
    Time: k + klogk
    Space: k + mn, using binary search to O(1); consider K > N
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null ||matrix[0].length == 0) {
            return Integer.MAX_VALUE;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
            }
        });
        boolean[][] visited = new boolean[row][col];

        queue.offer(new int[]{0, 0});
        int res = 0;

        while (k-- > 0) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            res = matrix[curRow][curCol];

            if (isValid(curRow + 1, curCol, row, col, visited)) {
                queue.offer(new int[]{curRow + 1, curCol});
                visited[curRow + 1][curCol] = true;
            }

            if (isValid(curRow, curCol + 1, row, col, visited)) {
                queue.offer(new int[]{curRow, curCol + 1});
                visited[curRow][curCol + 1] = true;
            }
        }
        return res;
    }

    private boolean isValid(int i, int j, int row, int col, boolean[][] visited) {
        return (i >= 0 && i < row && j >=0 && j < col && !visited[i][j]);
    }
}
