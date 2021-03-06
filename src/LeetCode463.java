public class LeetCode463 {
    /*
    for each land, add 4 - numbers of island neighbor;
    improve: since traverse left to right, up to down,
    if has up land neighbor, subtract 2, if has left, subtract 2
    Time: O(m*n), Space: O(1);
     */
    public int islandPerimeter(int[][] grid) {

//        int rows = grid.length;
//        int cols = grid[0].length;
//
//        int up, down, left, right;
//        int result = 0;
//
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                if (grid[r][c] == 1) {
//                    if (r == 0) { up = 0; }
//                    else { up = grid[r-1][c]; }
//
//                    if (c == 0) { left = 0; }
//                    else { left = grid[r][c-1]; }
//
//                    if (r == rows-1) { down = 0; }
//                    else { down = grid[r+1][c]; }
//
//                    if (c == cols-1) { right = 0; }
//                    else { right = grid[r][c+1]; }
//
//                    result += 4-(up+left+right+down);
//                }
//            }
//        }
//
//        return result;
        int rows = grid.length;
        int cols = grid[0].length;

        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    result += 4;

                    if (r > 0 && grid[r-1][c] == 1) {
                        result -= 2;
                    }

                    if (c > 0 && grid[r][c-1] == 1) {
                        result -= 2;
                    }
                }
            }
        }

        return result;
    }
}
