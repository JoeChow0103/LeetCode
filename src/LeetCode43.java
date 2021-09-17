public class LeetCode43 {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int len1 = num1.length(), len2 = num2.length();
        // construct an int array to store the digit for final result
        // the longest len of the possible final result is len1 + len2
        int nums[] = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int n1 = num1.charAt(i) - '0', n2 = num2.charAt(j) - '0';
                // the digit of (i + j) is determined by digit of (i + j + 1) and the cur mul
                // add the carry to the (i + j) for latter iteration
                nums[i + j] += (n1 * n2 + nums[i + j + 1]) / 10;
                // the digit of (i + j + 1) is determined by itself and the cur mul
                nums[i + j + 1] = (n1 * n2 + nums[i + j + 1]) % 10;
            }
        }

        // append the num to the stringbuilder for final transformation
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            // avoid extra 0 at head when the final result's length is less than len1 + len2
            if (sb.length() != 0 || num != 0) {
                sb.append(num);
            }
        }

        // transfer the sb to string
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
    }
}
