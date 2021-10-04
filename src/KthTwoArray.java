import java.util.Arrays;

public class KthTwoArray {
    /*
    充分利用两个数组都是有序的条件：
    1）当array1[k/2-1] == array2[k/2-1] 则返回array1[k/2-1]或者array2[k/2-1]；
    2）当array1[k/2-1] > array2[k/2-1]
    则array2在[0,k/2-1]范围内的元素一定比array1、array2合并后第k个元素小，可以不用考虑array2在[0,k/2-1]范围内的元素；
    3）当array1[k/2-1] < array2[k/2-1]
    则array1在[0,k/2-1]范围内的元素一定比array1、array2合并后第k个元素小，可以不用考虑array1在[0,k/2-1]范围内的元素。

    因此算法可以写成一个递归的形式，递归结束的条件为：
    1）array1[k/2-1] == array2[k/2-1] return array1[k/2-1]
    2）array1或者array2为空时，return array1[k-1]或者array2[k-1]
    3）k==1时，返回min(array1[0],array2[0])
     */
    static int kth(int[] arr1, int n1, int[] arr2, int n2, int k) {
        // keep n1 < n2, narrow the cases n1 < k < n2, n2 < k < n1 to n1 < k < n2
        if(n1 > n2) return kth(arr2, n2, arr1, n1, k);
        if(n1 == 0) return arr2[k - 1];
        if(k == 1) return Math.min(arr1[0], arr2[0]);

        // k1 number of nums in arr1 contribute to top k
        // k2 number of nums in arr2 contribute to top k
        int k1 = Math.min(k/2, n1);
        int k2 = k - k1;

        if (arr1[k1 - 1] > arr2[k2 - 1]) {// don't need to consider the [0, k2)
            int[] temp = Arrays.copyOfRange(arr2, k2, n2);
            return kth(arr1, n1, temp, n2 - k2, k - k2);
        } else if (arr1[k1 - 1] < arr2[k2 - 1]) {// don't need to consider the [0, k1)
            int[] temp = Arrays.copyOfRange(arr1, k1, n1);
            return kth(temp, n1 - k1, arr2, n2, k - k1);
        } else {
            return arr1[k1 - 1];
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr1 = {-1, 1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 7, 10, 12, 14, 16};
        System.out.println(kth(arr1, arr1.length, arr2, arr2.length, 4));
    }

}
