import java.util.Arrays;

public class LeetCode4 {
    /*
    two case to think: even/odd length of M+N
    If we merge two array, then we can see the left part is partly contributed by one and two.
    So we can find the pivot of array one which left part contribute to the left part,
    and remaining of the left part of the merged array is contributed by array two.
    Using binary search to do it, and each part should satisfy:
    L1 < R2 && L2 < R1, then we can find the pivot and decide what is median
    Time: O(logm), Space: O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int len = len1 + len2;
        if (len % 2 == 0) {
            int n1 = kth(nums1, len1, nums2, len2, len / 2);
            int n2 = kth(nums1, len1, nums2, len2, len / 2 + 1);
            return (n1 + n2) / 2.0;
        } else {
            int n1 = kth(nums1, len1, nums2, len2, len / 2 + 1);
            return n1;
        }
    }

    public int kth(int[] arr1, int n1, int[] arr2, int n2, int k) {
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

}
