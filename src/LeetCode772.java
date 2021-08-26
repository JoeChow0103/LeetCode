import java.util.*;

public class LeetCode772 {
    /*
    separate the string with parenthesis, in (), eval, then put the value back.
    in(), calculate the high priority first then others.
     */
    HashMap<Character, Integer> map = new HashMap<>(){{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
    }};
    public int calculate(String s) {
        Stack<Integer> numS = new Stack<>();
        Stack<Character> opS = new Stack<>();
        int len = s.length();
        int i = 0;
        opS.push('(');
        while (i < len) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                numS.push(num);
            } else if (map.containsKey(c)) {
                handleOp(numS, opS, c);
            } else if (c == '(') {
                opS.push('(');
            } else if (c == ')') {
                handleBrkt(numS, opS);
            } else {
                throw new IllegalArgumentException();
            }
            i++;
        }
        handleBrkt(numS, opS);
        return numS.peek();
    }
    private void handleOp(Stack<Integer> numS, Stack<Character> opS, char c) {
        while (!opS.isEmpty() && opS.peek() != '(') {
            if (map.get(opS.peek()) < map.get(c)) {
                break;
            } else {
                char op = opS.pop();
                int num2 = numS.pop();
                int num1 = numS.pop();
                int res = eval(op, num1, num2);
                numS.push(res);
            }
        }
        opS.push(c);
    }
    private void handleBrkt(Stack<Integer> numS, Stack<Character> opS) {
        while (!opS.isEmpty() && opS.peek() != '(') {
            char op = opS.pop();
            int num2 = numS.pop();
            int num1 = numS.pop();
            int res = eval(op, num1, num2);
            numS.push(res);
        }
        opS.pop();  // pop (
    }
    private int eval(char op, int num1, int num2) {
        if (op == '+') return num1 + num2;
        else if (op == '-') return num1 - num2;
        else if (op == '*') return num1 * num2;
        else if (op == '/') return num1 / num2;
        return 0;
    }
}
