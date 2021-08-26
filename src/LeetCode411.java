import java.util.*;

public class LeetCode411 {
    private class DictionaryPool {
        public int[] masks;
        public int size;

        public DictionaryPool(int size) {
            this.masks = new int[size];
            this.size = size;
        }

        public void add(int mask) {
            this.masks[size++] = mask;
        }

        public void remove(int idx) {
            int temp = masks[size - 1];
            masks[size - 1] = masks[idx];
            masks[idx] = temp;
            size--;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }

    private int minLen;
    private int res;

    private void helper(String target, int idx, int path, int curLen, DictionaryPool pool) {
        if (curLen > minLen)    return;

        int len = target.length();
        if (pool.isEmpty()) {
            if (idx < len)  curLen++;
            if (curLen < minLen) {
                minLen = curLen;
                path = path << (len - idx);
                res = path;
            }
            return;
        }

        if (idx >= len)   return;

        // add a number -- 0
        for (int i = idx; i < len; i++) {
            // previous index is a char, not a digit. Or an empty path
            if (curLen == 0 || (path & 1) == 1) {
                helper(target, i + 1, path << (i + 1 - idx), curLen + 1, pool);
            }
        }

        // add a char -- 1
        int i = 0, curSize = pool.size;
        while (i < pool.size) {
            int mask = pool.masks[i];
            int offset = len - 1 - idx;
            if ((mask & (1 << offset)) == 0) {
                pool.remove(i);
            } else {
                i++;
            }
        }

        helper(target, idx + 1, (path << 1) | 1, curLen + 1, pool);

        pool.setSize(curSize);
    }

    private int getMask(String target, String s) {
        int mask = 0, len = target.length();
        for (int i = 0; i < len; i++) {
            mask <<= 1;
            if (s.charAt(i) == target.charAt(i)) {
                mask |= 1;
            }
        }
        return mask;
    }

    private String convertMask(String target, int mask) {
        StringBuilder sb = new StringBuilder();
        int len = target.length();
        int m = 1 << (len - 1), count = 0;

        for (int i = 0; i < len; i++, m = m >> 1) {
            if ((mask & m) == 0) {
                count++;
            } else {
                if (count > 0)  sb.append(count);
                count = 0;
                sb.append(target.charAt(i));
            }
        }

        if (count > 0)  sb.append(count);

        return sb.toString();
    }

    public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || target.length() == 0) return "";
        if (dictionary == null || dictionary.length == 0)   return String.valueOf(target.length());

        int len = target.length();
        int dicSize = dictionary.length;
        DictionaryPool pool = new DictionaryPool(dicSize);
        pool.size = 0;
        for (int i = 0; i < dictionary.length; i++) {
            String s = dictionary[i];
            if (s.length() == len) {
                pool.add(getMask(target, s));
            }
        }

        minLen = len;
        res = (1 << len) - 1;
        helper(target, 0, 0, 0, pool);

        return convertMask(target, res);
    }
}
