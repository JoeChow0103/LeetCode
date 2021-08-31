public class LeetCode490 {
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        if (maze[start[0]][start[1]] == 1) return false;
        return dfs(maze, start, destination,visited);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        int rows = maze.length, cols = maze[0].length;
        int i = start[0], j = start[1];

        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        if ((i < 0 || i >= rows || j < 0 || j >= cols) || visited[start[0]][start[1]]) return false;

        visited[start[0]][start[1]] = true;
        for (int[] dir : dirs) {
            int ii = dir[0] + i;
            int jj = dir[1] + j;
            while (!(ii < 0 || ii >= rows || jj < 0 || jj >= cols) && maze[ii][jj] != 1) {
                ii = dir[0] + ii;
                jj = dir[1] + jj;
            }
            if (dfs(maze, new int[]{ii - dir[0], jj - dir[1]}, destination, visited)) {
                return true;
            }
        }
        return false;
    }
}
