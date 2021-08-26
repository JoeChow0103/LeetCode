import java.util.*;

public class LeetCode752 {
    public int openLock(String[] deadends, String target) {
        // c.c.

        HashSet<String> deadSet = new HashSet<>();
        for (String de : deadends) {
            if (de.equals("0000")) {
                return -1;
            }
            deadSet.add(de);
        }

        HashSet<String> visited = new HashSet<>();
        Queue<char[]> queue = new LinkedList<>();
        queue.add(new char[]{'0','0','0','0'});
        visited.add("0000");
        int minLen = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                char[] cur = queue.poll();

                List<char[]> nexts = convert(cur, deadSet, visited);
                for (char[] next : nexts) {
                    String nextString = String.valueOf(next);
                    if (nextString.equals(target)) {
                        return minLen + 1;
                    }
                    queue.offer(next);
                    visited.add(nextString);
                }
            }
            minLen++;
        }

        return -1;
    }

    private List<char[]> convert(char[] cs, HashSet<String> deadSet, HashSet<String> visited) {
        List<char[]> ret = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            char tmp = cs[i];

            // clockwise
            if (tmp == '9') {
                cs[i] = '0';
            } else {
                cs[i] = (char) (tmp + 1);
            }

            String str = String.valueOf(cs);
            if (!deadSet.contains(str) && !visited.contains(str)) {
                ret.add(cs.clone());
            }

            // anti-clockwise
            if (tmp == '0') {
                cs[i] = '9';
            } else {
                cs[i] = (char) (tmp - 1);
            }

            str = String.valueOf(cs);
            if (!deadSet.contains(str) && !visited.contains(str)) {
                ret.add(cs.clone());
            }

            cs[i] = tmp;
        }

        return ret;
    }
}
