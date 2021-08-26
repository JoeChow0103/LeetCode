import java.util.*;

public class LeetCode407 {
    /*
    from boarder to the center, calculate the exterior height - interior height;
    update the interior, max(exterior.height, interior) because the outsider decide insider
    O(m*n*lg(m+n))
     */
    private class Cell implements Comparable<Cell> {
        int i, j, height;

        public Cell(int i, int j, int height) {
            this.i = i;
            this.j = j;
            this.height = height;
        }

        public int compareTo(Cell that) {
            return this.height - that.height;
        }
    }

    public int trapRainWater(int[][] heightMap) { // O(m*n*lg(m+n))
        if (heightMap == null || heightMap[0].length == 0 ||
        heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }

        int row = heightMap.length, col = heightMap[0].length;

        PriorityQueue<Cell> heap = new PriorityQueue<>(); //minHeap
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) { // offer boarder
            heap.offer((new Cell(i, 0, heightMap[i][0])));
            visited[i][0] = true;
            heap.offer(new Cell(i, col - 1, heightMap[i][col - 1]));
            visited[i][col - 1] = true;
        }
        for (int j = 0; j < col; j++) {
            heap.offer((new Cell(0, j, heightMap[0][j])));
            visited[0][j] = true;
            heap.offer(new Cell(row - 1, j, heightMap[row - 1][j]));
            visited[row - 1][j] = true;
        }

        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int ret = 0;
        while (!heap.isEmpty()) {
            Cell cur = heap.poll();
            for (int[] dir : direction) {
                int rowIdx = cur.i + dir[0], colIdx = cur.j + dir[1];
                if (rowIdx >= 0 && rowIdx < row && colIdx >= 0 && colIdx < col &&
                !visited[rowIdx][colIdx]) {
                    visited[rowIdx][colIdx] = true;
                    ret += Math.max(0, cur.height - heightMap[rowIdx][colIdx]);
                    heap.offer(new Cell(rowIdx, colIdx, Math.max(cur.height, heightMap[rowIdx][colIdx])));
                }
            }
        }
        return ret;
    }
}
