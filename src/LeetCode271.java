import java.util.ArrayList;
import java.util.List;

public class LeetCode271 {
}

class StringCodec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == ',') sb.append("&,");
                else if (c == '&') sb.append("&&");
                else sb.append(c);
            }
            sb.append(",");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<>();
        int i = 0, len = s.length();
        while (i < len) {
            StringBuilder sb = new StringBuilder();
            while (i < len && s.charAt(i) != ',') {
                char c = s.charAt(i);
                if (c == '&') sb.append(s.charAt(++i));
                else sb.append(c);
                i++;
            }
            strs.add(sb.toString());
            i++;
        }
        return strs;
    }
}
