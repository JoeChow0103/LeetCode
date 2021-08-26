import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] < e2[0]) {
                    return -1;
                } else if (e1[0] > e2[0]) {
                    return 1;
                } else {
                    return e2[1] - e1[1];
                }
            }
        });
        return lengthOfLIS(envelopes);
    }

    public int lengthOfLIS(int[][] envelopes) {
        List<Integer> buffer = new ArrayList<>();
        for (int[] e : envelopes) {
            int n = e[1];
            if (buffer.size() == 0) {
                buffer.add(n);
            } else {
                if (buffer.get(buffer.size() - 1) < n) {
                    buffer.add(n);
                } else {
                    int index = replaceIndex(buffer, n);
                    buffer.set(index, n);
                }
            }
        }
        return buffer.size();
    }

    // find the index of "first Next" val: "Next" val >= val
    private int replaceIndex(List<Integer> buffer, int val) {
        int start = 0, end = buffer.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midVal = buffer.get(mid);
            if (midVal == val) {
                return mid;
            } else if (midVal < val) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
