import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class LeetCode480 {
    /*
    prepare two data structure: left store large to small, right store small to large
    each time: left poll the largest to right, until left_size <= right_size, calculate
    the median by (left.poll() + right.poll())/2 or (right.poll)
    if i >= k, then remove element nums[i - k],
    Caution: treeSet only store the index, because the property of set: no duplicate
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] result = new double[len - k + 1];
        Comparator<Integer> comparator = (a, b)
                -> nums[a] != nums[b]
                ? Integer.compare(nums[a], nums[b])
                : a - b;
        // left large to small, right small to large -> idx, val
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);

        Callable<Double> getMedian = ()
                -> {
            while (left.size() > right.size()) {
                right.add(left.pollFirst());
            }
            return (k % 2 == 0)
                    ? ((double) nums[left.first()] + (double) nums[right.first()]) / 2.0
                    : (double) nums[right.first()];
        };

        for (int i = 0; i < len; i++) {
            if (i >= k) {
                if (!left.remove(i - k)) {
                    right.remove(i - k);
                }
            }

            right.add(i);
            left.add(right.pollFirst());

            if (i >= k - 1) {
                try {
                    result[i - k + 1] = getMedian.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
