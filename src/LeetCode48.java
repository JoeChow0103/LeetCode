public class LeetCode48 {
    /*
    rotate from the most exterior then the second exterior,
    always do the same thing, but the start point different.
    Time: O(n*n), Space: O(1)
     */
    public void rotate(int[][] matrix) {
        helper(matrix, 0, matrix.length);

    }
    private void helper(int[][] matrix, int s, int w){
        if(w <= 1){
            return;
        }

        for(int i = 0; i < w - 1; i++){
            int temp = matrix[s][s + i];
            matrix[s][s + i] = matrix[s + w - 1 - i][s];
            matrix[s + w - 1 - i][s] = matrix[s + w - 1][s + w - 1 - i];
            matrix[s + w - 1][s + w - 1 - i] = matrix[s + i][s + w - 1];
            matrix[s + i][s + w - 1] = temp;
        }
        helper(matrix, s + 1, w - 2);
    }
}
