public class LeetCode274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // counting papers for each citation number
        for (int citation : citations) {
            if (citation > n) papers[n]++;
            else papers[citation]++;
        }

        int h = n;
        for (int sum = papers[n]; sum < h; sum += papers[h]) {
            h--;
        }
        return h;
    }
}
