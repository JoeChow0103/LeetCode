public class LeetCode32 {
    /*
    check from the right, then from the left. update the max. two path
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                max = Math.max(max, 2 * right);
            }else if(right > left){
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                max = Math.max(max, 2 * left);
            }else if(left > right){
                left = 0;
                right = 0;
            }
        }
        return max;
    }

    public int lVP(String s) {
        int len = s.length(), max = 0;
        int m[] = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                m[i] = 0;
            } else if (s.charAt(i) == '(') {
                int j = i + m[i + 1] + i;
                if (j < len && s.charAt(j) == ')') {
                    m[i] = m[i + 1] + 2;
                    if (j + 1 < len) {
                        m[i] += m[j + 1];
                    }
                }
            }
            max = Math.max(max, m[i]);
        }
        return max;
    }
}
