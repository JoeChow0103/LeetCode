public class LeetCode135 {
    public static int candy(int[] ratings) {
        // since the loop can only compare the one and the next one
        // so this need two loops from left and from right to update the compensation

        // construct compensation array from left
        int leftwardCompensations[] = new int[ratings.length];
        leftwardCompensations[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            // if left < right, then compensation[right] = compensation[left] + 1
            if (ratings[i - 1] < ratings[i]) leftwardCompensations[i] = leftwardCompensations[i - 1] + 1;
            else leftwardCompensations[i] = 1;
        }

        // construct compensation array from right
        int rightwardCompensations[] = new int[ratings.length];
        rightwardCompensations[ratings.length - 1] = 1;
        for (int i = ratings.length - 2; i >= 0; i--) {
            // if right < left, then compensation[left] = compensation[right] + 1;
            if (ratings[i + 1] < ratings[i]) rightwardCompensations[i] = rightwardCompensations[i + 1] + 1;
            else rightwardCompensations[i] = 1;
        }

        // calculate the total compensation by choose the bigger compensation in the left&right array
        int compensation = 0;
        for (int i = 0; i < ratings.length; i++) {
            compensation += Math.max(rightwardCompensations[i], leftwardCompensations[i]);
        }
        return compensation;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 2, 3, 4};
        System.out.println(candy(ratings));
    }
}
