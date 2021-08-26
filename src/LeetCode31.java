public class LeetCode31 {
    /*
    from the right, find the first decreasing number a,
    then from the right, find a number b just larger than a,
    swap the a and b, then reverse the substring from b's left
     */
    public void nextPermutation(int[] nums) {
        if (nums == null) return;

        int len = nums.length;
        int i = 0;
        for (i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

        if (i >= 0) {
            for (int j = len - 1; j >= i + 1; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    break;
                }
            }
        }

        int l = i + 1, r = len - 1;
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }

    }
}
