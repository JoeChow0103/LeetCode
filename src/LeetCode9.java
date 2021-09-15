public class LeetCode9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int temp = x;
        int left = 0;
        while (x > 0) {
            left = left * 10 + x % 10;
            x = x / 10;
        }
        return temp == left;
    }
}
