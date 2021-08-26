public class LeetCode66 {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0)    return null;

        int len = digits.length, carry = 1;
        for(int i = len - 1; i >= 0; i--){
            int n = digits[i] + carry;
            digits[i] = n % 10;
            carry = n / 10;
        }

        if(carry == 0)  return digits;

        int[] res = new int[len + 1];
        res[0] = carry;
        for(int i = 1; i <= len; i++) res[i] = digits[i - 1];
        return res;
    }
}
