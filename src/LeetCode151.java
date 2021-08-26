public class LeetCode151 {
    /*
    solution1: split then reverse
    solution2: rotate then reverse
    solution3: dequeue
     */
//    solution 1: Build-in split
//    public String reverseWords(String s) {
//        // c.c.
//        if (s == null || s.length() == 0) return s;
//
//        String[] array = s.trim().split("\\s+");
//        int left = 0, right = array.length - 1;
//
//        while (left < right) {
//            swap(array, left++, right--);
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        for (String word : array) {
//            sb.append(word);
//            sb.append(' ');
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        return sb.toString();
//    }
//
//    private void swap(String[] array, int i, int j) {
//        String temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
//    }

//    solution 2: reverse the whole string then reverse each word

    public String reverseWords(String s) {
        // c.c.
        if (s == null || s.length() == 0) return s;
        String str = cleanExtraSpace(s);
        String reversedStr = reverse(str);
        String words = reverseEachWord(reversedStr);
        return words;
    }

    private String cleanExtraSpace(String s) {
        // c.c.
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            left++;
        }
        return sb.toString();
    }

    private String reverse(String s) {
        if (s == null || s.length() == 0) return "";
        int left = 0, right = s.length() - 1;
        char[] array = s.toCharArray();
        while (left < right) {
            swap(array, left++, right--);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append(c);
        }
        return sb.toString();
    }

    private String reverseEachWord(String s) {
        if (s == null || s.length() == 0) return "";
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (array[i] == ' ') {
                continue;
            } else {
                int j = i + 1;
                while (j < array.length && array[j] != ' ') {
                    j++;
                }
                int left = i, right = j - 1;
                while (left < right) {
                    swap(array, left++, right--);
                }
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append(c);
        }
        return sb.toString();
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
