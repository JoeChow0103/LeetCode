public class LeetCode214 {
    /*
    s = A'AB
    r = B'A'A
    found s's prefix is r's suffix
    use KMP found String (s+#+r)'s
     */
    public static String shortestPalindrome(String s) {
        String str = s + "#" + new StringBuilder(s).reverse().toString();
        char[] arr = str.toCharArray();
        int m = str.length();
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && arr[i] != arr[j]) {
                j = next[j - 1];
            }
            if (arr[i] == arr[j]) j++;
            next[i] = j;
        }
//        for (int n : next) System.out.println(n);
        int index = next[next.length - 1];
        String res = new StringBuilder(s.substring(index)).reverse().toString() + s;
        return res;
    }

    public static void main(String[] args) {
        shortestPalindrome("aacecaaa");
    }
}
