import java.util.Arrays;
import java.util.HashMap;

public class LeetCode164 {
    /*
    bucket sort: to find each min and max of bucket and compare the gap between buckets
     */
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        // calculate the min and max of nums
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // case1: min == max
        if (min == max) return 0;

        // store the min and max for each bucket
        int bucketSize = (max - min) / n + 1;
        int bucketNumber = (max - min) / bucketSize + 1;
        // int[][] buckets = new int[bucketNumber][bucketSize];
        int[] minBuckets = new int[bucketNumber];
        int[] maxBuckets = new int[bucketNumber];
        Arrays.fill(minBuckets, Integer.MAX_VALUE);
        Arrays.fill(maxBuckets, Integer.MIN_VALUE);
        for (int num : nums) {
            int bucketIdx = (num - min) / bucketSize;
            minBuckets[bucketIdx] = Math.min(minBuckets[bucketIdx], num);
            maxBuckets[bucketIdx] = Math.max(maxBuckets[bucketIdx], num);
        }

        // find the gap of bucket
        int maxGap = Integer.MIN_VALUE, prevMin = Integer.MAX_VALUE;
        for (int i = 0; i < bucketNumber; i++) {
            if (minBuckets[i] == Integer.MAX_VALUE && maxBuckets[i] == Integer.MIN_VALUE) {
                continue;
            }
            if (prevMin == Integer.MAX_VALUE) {
                prevMin = maxBuckets[i];
            } else {
                maxGap = Math.max(maxGap, minBuckets[i] - prevMin);
                prevMin = maxBuckets[i];
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 100};
        System.out.println(maximumGap(nums));
    }
}
