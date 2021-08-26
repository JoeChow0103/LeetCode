import java.util.Random;

public class LeetCode384 {
    /*
    one pointer traverse, swap the number to random left number
     */
    int[] nums;
    public LeetCode384(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffles = new int[this.nums.length];
        for(int i = 0; i < this.nums.length; i++) {
            shuffles[i] = nums[i];
        }
        for(int i = 0; i < shuffles.length; i++) {
            Random rand = new Random();
            int next = rand.nextInt(i+1);
            int temp = shuffles[i];
            shuffles[i] = shuffles[next];
            shuffles[next] = temp;
        }
        return shuffles;

    }
}
