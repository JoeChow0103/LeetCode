import java.util.Arrays;

public class CountNumOfTriangle {
    int[] canMakeTriangle(int[] arr) {
        int len = arr.length;
        if (len < 3) {
            return new int[len];
        }
        int[] res = new int[len - 2];

        for (int i = 0; i < len - 2; i++) {
            int l1 = arr[i], l2 = arr[i + 1], l3 = arr[i + 2];
            if (isTriangle(l1, l2, l3)) {
                res[i] = 1;
            } else {
                res[i] = 0;
            }
        }

        return res;
    }

    boolean isTriangle(int l1, int l2, int  l3) {
        return (l1 + l2) > l3 && (l2 + l3) > l1 && (l1 + l3) > l2;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 4};
        CountNumOfTriangle solution = new CountNumOfTriangle();
        int[] res = solution.canMakeTriangle(arr);
        for (int n : res) System.out.println(n);
    }
}
