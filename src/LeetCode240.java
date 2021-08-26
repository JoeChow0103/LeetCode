public class LeetCode240 {
    /*
    search from the last row first number, if the number is bigger than the target, move to the above row;
    if the number is smaller than the target, move to the next number.
    Time: O(m + n), Space: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int rowIndex = row - 1;
        int colIndex = 0;

        while (rowIndex >= 0 && colIndex < col) {
            int num = matrix[rowIndex][colIndex];
            if (num == target) return true;
            else if (num > target) {
                rowIndex--;
            } else {
                colIndex++;
            }
        }
        return false;
    }
}
