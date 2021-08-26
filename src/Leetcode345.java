import java.util.HashSet;

public class Leetcode345 {
    public String reverseVowels(String s) {
        int len = s.length();
        int start = 0, end = len - 1;
        char[] cs = s.toCharArray();

        HashSet<Character> set = new HashSet<>();
        set.add('a'); // A E I O U ?
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        while (start < end) {
            // find the vowel
            while (start < end && !set.contains(cs[start])) start++;
            while (start < end && !set.contains(cs[end])) end--;

            if (start < end) {
                char temp = cs[start];
                cs[start++] = cs[end];
                cs[end--] = temp;
            }
        }
        return String.valueOf(cs);
    }
}
