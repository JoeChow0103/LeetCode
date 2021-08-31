import java.util.LinkedList;
import java.util.Queue;

public class LeetCode994 {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;
        int row = grid.length;
        int col = grid[0].length;
        int minutes = 0;
        boolean hasFresh = false;
        boolean[][] visited = new boolean[row][col];
        int[][] directions = new int[][] {{-1,0}, {1,0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 2){
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
                if(grid[i][j] == 1) hasFresh = true;
            }
        }
        if(!hasFresh) return 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.poll();
                for(int[] dir : directions){
                    int newI = cur[0] + dir[0];
                    int newJ = cur[1] + dir[1];
                    if(newI >= 0 && newI < row && newJ >= 0 && newJ < col && !visited[newI][newJ] && grid[newI][newJ] == 1){
                        queue.offer(new int[] {newI, newJ});
                        visited[newI][newJ] = true;
                    }
                }
            }
            minutes++;
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1 && !visited[i][j]) return -1;
            }
        }
        return minutes - 1;
    }
}
