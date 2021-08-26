import java.util.*;

public class LeetCode273 {
    /*
    split by every three digits, check the length.
    If len > 9, then has billion, million,
    If len <= 9 and len > 6, then has million,
    If len <= 6 and len > 3, then has thousand
    else read.
    Three digit part will be process separately. Hundred + Tens + one.
    Do multiple time, it should be separated from the main method.
     */
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String[] weights = {"", "Thousand", "Million", "Billion", ""};
        Stack<Integer> stack = new Stack<>();
        while (num != 0) { // seperate the number by three digits
            stack.push(num % 1000);
            num /= 1000;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if (cur != 0) {
//                String threeDidit = threeDigit(cur);
//                sb.append(threeDidit);
                sb.append(readThree(cur)).append(" ").append(weights[stack.size()]).append(" ");
            }
        }
        while (sb.charAt(sb.length() - 1) == ' ') { // remove redundant space
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    private String readThree(int num){
        if(num >= 0 && num <= 9){
            return readOne(num);
        }
        if(num >= 10 && num <= 99){
            return readTwo(num);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(readOne(num / 100)).append(" Hundred ").append(readTwo(num % 100));
        while(sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();


    }

    private String readTwo(int num){
        if(num >= 0 && num <= 9){
            return readOne(num);
        }
        String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        if(num >= 10 && num <= 19){
            return teens[num % 10];
        }

        String[] ties = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        StringBuilder sb = new StringBuilder();
        sb.append(ties[num / 10]).append(" ").append(readOne(num % 10));
        while(sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();


    }

    private String readOne(int num){
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return ones[num];

    }
}
