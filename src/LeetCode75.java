public class LeetCode75 {
    /*
    if 1, swap cur to the left; if 2, swap cur to the right.
    solution1: sort
    solution2: select sort, slow fast
    solution3: count sort
    solution4: pair quick sort
    solution5: three pointers
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int start = -1, end = len, i = 0; // avoid swap method
        while (i < end) {
            if (nums[i] == 0) {
                nums[i++] = 1;
                nums[++start] = 0;
            } else if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                nums[i] = nums[--end];
                nums[end] = 2;
            }
        }
    }
}
