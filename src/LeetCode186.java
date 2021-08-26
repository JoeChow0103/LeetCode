public class LeetCode186 {
    public void reverseWords(char[] s) {
        //cc
        if(s == null || s.length <= 1) return;

        reverse(s, 0, s.length - 1);

        int left = 0;
        for(int right = 1; right <= s.length; right++){
            if(right == s.length || s[right] == ' '){
                reverse(s, left, right - 1);
                left = right + 1;
            }
        }
    }

    private void reverse(char[] s, int left, int right){
        while(left < right){
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
