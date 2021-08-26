import java.util.*;

public class LeetCode542 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] updateMatrix(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return matrix;
        }

        int row = matrix.length, col = matrix[0].length;
        Queue<Integer> queue = new LinkedList<>();
        addAllZeros(matrix, queue);
        HashSet<Integer> set = new HashSet<>();
        int minLen = 1; //

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    int next = ii * col + jj;
                    if (ii >= 0 && ii < row
                            && jj >=0 && jj < col
                            && matrix[ii][jj] == 1
                            && !set.contains(ii * col + jj)) {
                        queue.offer(next);
                        matrix[ii][jj] = minLen;
                        set.add(next); // the idea is to add with the first visit,so this line
                    }
                }
            }
            minLen++;
        }
        return matrix;
    }

    private void addAllZeros(int[][] matrix, Queue<Integer> queue) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode542 solution = new LeetCode542();
        int[][] matrix = {{0, 0, 0},
                          {0, 1, 0},
                          {1, 1, 1}};
        int[][] solMatrix = solution.updateMatrix(matrix);
        int row = solMatrix.length;
        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(solMatrix[i]));
        }
    }
}
