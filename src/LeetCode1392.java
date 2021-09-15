public class LeetCode1392 {
    public static String longestPrefix(String s) {
        char[] arr = s.toCharArray();
        int m = arr.length;
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && arr[i] != arr[j]) {
                j = next[j - 1];
            }
            if (arr[i] == arr[j]) {
                j++;
            }
            next[i] = j;
        }
        return s.substring(m - next[m - 1]);
    }

    public static void main(String[] args) {
        System.out.println(longestPrefix("level"));
    }
}
