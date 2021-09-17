import java.util.HashMap;

public class LeetCode38 {
    public String countAndSay(int n) {
        String s = "1";
        while (--n > 0) {
            String temp = "";
            char c = '\0';
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (i == 0) {
                    c = s.charAt(i); // record the char first appear
                    count = 1; // update the count by 1
                } else if (s.charAt(i) == s.charAt(i - 1)) {
                    count++; // count how many the char appear
                } else if (s.charAt(i) != s.charAt(i - 1)) {
                    temp += say(c, count); // update the current path
                    count = 1; // set the count back to 1
                    c = s.charAt(i); // set the c to the current char
                }
            }
            temp += say(c, count);
            s = temp;
        }
        return s;
    }

    private String say(char c, int count) {
        return String.valueOf(count) + c;
    }
}
