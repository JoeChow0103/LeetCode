import java.util.*;

public class LeetCode20 {
    /*
    when one kind open is used, then next must not be other kind close.
    But it could be
    1. same close
    2. different open
    there are three kinds of parentheses, use hashmap to recognize and use stack to store.
    Time: O(n), Space: O(n);
    improve, don't use hashmap, directly check, push the value of the key to push, then check
    the next is the same
     */
    public boolean isValid(String s) {
        if(s == null || s.length()==0) return true;
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else {
                if(stack.isEmpty() || stack.pop()!=c){
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
}
