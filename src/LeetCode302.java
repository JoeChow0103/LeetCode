public class LeetCode302 {
    public int minArea(char[][] image, int x, int y) {
        int row = image.length, col= image[0].length;
        int start = 0, end = 0;
        int left = 0, right = 0, top = 0, bottom = 0;

        // left: 0 ~ y
        start = 0;
        end = y;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < row; i++) {
                if (image[i][mid] == '1') {
                    end = mid - 1;
                    break;
                }
            }
            if (i == row) start = mid + 1;
        }
        left = start;

        // right : y ~ col-1
        start = y;
        end = col - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < row; i++) {
                if (image[i][mid] == '1') {
                    start = mid + 1;
                    break;
                }
            }
            if (i == row) end = mid - 1;
        }
        right = end;

        // top: 0 ~ x
        start = 0;
        end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < col; i++) {
                if (image[mid][i] == '1') {
                    end = mid - 1;
                    break;
                }
            }
            if (i == col) start = mid + 1;
        }
        top = start;

        // bottom : x ~ row-1
        start = x;
        end = row - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < col; i++) {
                if (image[mid][i] == '1') {
                    start = mid + 1;
                    break;
                }
            }
            if (i == col) end = mid - 1;
        }
        bottom = end;

        return (right - left + 1) * (bottom - top + 1);
    }
}
