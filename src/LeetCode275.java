public class LeetCode275 {
    public int hIndex(int[] citations) {
        int start = 0, end = citations.length - 1;
        int len = citations.length;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (len - mid > citations[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return len - start;
    }
}
