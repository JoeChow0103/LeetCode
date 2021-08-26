import java.util.*;

public class LeetCode224 {
    /*
    why stack? need to calculate with high priority then put then calculate others
    how demo: two stack 错位store. demo with construction
    1. how store +/- and (), ( is 0,1
    2. how initial push. 0,1.
    3. when end, why push + at end.
    But cannot solve -1 + 2, so access the sign and number first then update result
    Time: O(n), Space: O(n)
     */
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;

        Stack<Integer> nums = new Stack<>();
        int result = 0;
        int sign = 1;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = c - '0';
                i++;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                result += num * sign;
            }else if(c == '+'){
                sign = 1;
            }else if(c == '-'){
                sign = -1;
            }else if(c == '('){
                nums.push(result);
                nums.push(sign);
                result = 0;
                sign = 1;

            }else if(c == ')'){
                result = result * nums.pop() + nums.pop();
            }

        }
        return result;
    }
}
