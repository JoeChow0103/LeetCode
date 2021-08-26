public class LeetCode334 {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int third = nums[i];
            if (third > second) {
                return true;
            } else if (third <= first) { // update first firstly
                first = third;
            } else { // third > first, third <= second, update second secondly
                second = third;
            }
        }
        return false;
    }
}
