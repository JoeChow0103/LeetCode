import java.util.Arrays;

public class LeetCode223 {
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int leftX = Math.max(ax1, bx1);
        int rightX = Math.min(ax2, bx2);
        int bottomY = Math.max(ay1, by1);
        int topY = Math.min(ay2, by2);

        int insectArea = 0;
        if (leftX < rightX && bottomY < topY) {
            insectArea = (rightX - leftX) * (topY - bottomY);
        }
        return area1 + area2 - insectArea;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
