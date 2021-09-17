public class LeetCode73 {
    /*
    each 0 not boarded can determine the is left and top board will be 0;
    and the 0 on board can determine the down and right will be 0;
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        // firstRowZero and firstColZero determine if firstRow and firstCol is all 0
        boolean firstRowZero = false, firstColZero = false;
        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int j = 0; j < row; j++) {
            if (matrix[j][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // mark the internal 0, and mark the related row/col board is 0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // update all the point with its related board is 0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // update the first col and row if it has zero originally
        if (firstColZero) {
            for (int j = 0; j < row; j++) {
                matrix[j][0] = 0;
            }
        }
        if (firstRowZero) {
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}
