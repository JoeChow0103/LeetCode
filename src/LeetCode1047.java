public class LeetCode1047 {
    /*
    solution1: hashMap replace
    solution2: stack
    solution3: slow fast pointer
     */
    public String removeDuplicates(String s) {
//         // corner case
//         if (S == null || S.length() == 0) return "";

//         Stack<Character> stack = new Stack<>();
//         for (int i = 0; i < S.length(); i++) {
//             char c = S.charAt(i);
//             if (stack.isEmpty()) {
//                 stack.push(c);
//                 continue;
//             }
//             if (stack.peek() == c) stack.pop();
//             else stack.push(c);
//         }
//         StringBuilder sb = new StringBuilder();
//         while (!stack.isEmpty()) sb.append(stack.pop());
//         return sb.reverse().toString();

        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] words = s.toCharArray();

        // [0, slow] no adjacent duplicate so far
        // (slow, fast) explored and will be removed
        //[fast, length) to be checked
        //
        int slow = -1;
        int fast = 0;
        int length = words.length;
        while (fast < length) {
            if (slow == -1) {
                slow++;
                words[slow] = words[fast];
                fast++;
            } else if (words[fast] == words[slow]) {
                fast++;
                slow--;
            } else {
                slow++;
                words[slow] = words[fast];
                fast++;
            }
        }
        length = slow + 1;
        return String.valueOf(words, 0, length);
        // return S;
    }
}
