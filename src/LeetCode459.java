public class LeetCode459 {
    /*
    a b c a d c a b c a d c
    0 0 0 1 0 0 1 2 3 4 5 6
    //Create one KMP table which will tell us about LPS in the string.
    //Find the length of the pattern (if formed).
    //check whether that pattern length is divisible by the length of the given string.
    //Note:- we need to check the condition of the last index of LPS array. If that is 0 then it means that there is
    //no pattern formed in the string.
     */
    public static boolean repeatedSubstringPattern(String s) {
        char[] arr = s.toCharArray();
        int m = arr.length;
        int[] next = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && arr[i] != arr[j]) {
                j = next[j];
            }
            if (arr[i] == arr[j]) j++;
            next[i] = j;
        }
        for (int i : next) System.out.println(i);
        if (next[m - 1] == 0) return false;
        int patternLen = s.length() - next[m - 1];
        if (m % patternLen == 0) return true;
        return false;
    }

    public static void main(String[] args) {
        repeatedSubstringPattern("ababba");
    }
}
