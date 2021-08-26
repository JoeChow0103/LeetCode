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
        if (len1 > len2) return findMedianSortedArrays(nums2, nums1);
        if (len1 == 0) return ((double) nums2[(len2 - 1) / 2] + (double) nums2[len2 / 2]) / 2;
        int len = len1 + len2;
        int left = 0, right = len1;
        int cut1, cut2;
        while (left <= right) {
            cut1 = (left + right) / 2;
            cut2 = (len + 1) / 2 - cut1;
            double l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double r1 = (cut1 == len1) ? Integer.MAX_VALUE : nums1[cut1];
            double r2 = (cut2 == len2) ? Integer.MAX_VALUE : nums2[cut2];
            if (l1 > r2) {right = cut1 - 1;}
            else if ( l2 > r1) {left = cut1 + 1;}
            else {
                if (len % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
                } else {
                    return Math.max(l1, l2);
                }
            }
        }
        return -1;
    }

}
