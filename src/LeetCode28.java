public class LeetCode28 {
    /*
    two pass to find the first char(don't need in code), then check if traverse
    to the last char of the needle. Otherwise, not found: len > bound and not match
     */
    public int strStr(String haystack, String needle) {
        // c.c.
        // int len1 = s1.length(); // wrong cannot solve null and not null
//         if (haystack == null || haystack.length() == 0 || needle == null || needle.length() == 0) return -1;

//         if (needle.length() > haystack.length()) return strStr(needle, haystack);

//         for (int i = 0; i <= haystack.length() - needle.length(); i++) {
//             int j;
//             for (j = 0; j < needle.length(); j++) {
//                 if (haystack.charAt(i + j) != needle.charAt(j)) break;
//                 if (j == needle.length() - 1) return i; // ****
//                 }
//             if (j == needle.length()) return i;
//         }
//         return -1;
        // so let it move one more step to len
        // c.c.
        for (int i = 0; i <= haystack.length(); i++) {
            for (int j = 0; j <= needle.length(); j++) {
                // 注意这个条件顺序
                if (j == needle.length()) return i;
                if ((i + j) >= haystack.length()) return -1;
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
        return -1;
    }
}
