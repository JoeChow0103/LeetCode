import java.util.*;

public class LeetCode227 {
//    /*
//    why initial temp sum = 0, sum = 0
//     */
//    public int calculate(String s) {
//        //c.c.
//        int sum = 0;
//        int tempSum = 0;
//        int num = 0;
//        char lastSign = '+';
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (Character.isDigit(c)) num = num * 10 + c - '0';
//            if (i == s.length() - 1 || !Character.isDigit(c) && c!=' ') {
//                switch(lastSign) {
//                    case '+':
//                        sum+=tempSum;
//                        tempSum = num;
//                        break;
//                    case '-':
//                        sum+=tempSum;
//                        tempSum = -num;
//                        break;
//                    case '*':
//                        tempSum *= num;
//                        break;
//                    case '/':
//                        tempSum /= num;
//                        break;
//                }
//                lastSign = c;
//                num=0;
//            }
//        }
//        sum+=tempSum;
//        return sum;
//    }
    /*
    process the high priority first then low priority
     */
public int calculate(String s) {
    if(s == null || s.length() == 0){
        return 0;
    }
    Map<Character, Integer> priorityMap = new HashMap<>();
    priorityMap.put('+', 1);
    priorityMap.put('-', 1);
    priorityMap.put('*', 2);
    priorityMap.put('/', 2);

    Stack<Integer> numStack = new Stack<>();
    numStack.push(0);
    Stack<Character> oprtStack = new Stack<>();

    int index = 0;
    while(index < s.length()){
        char c = s.charAt(index);
        if(c == ' '){
            index++;
        }else if(Character.isDigit(c)){
            int num = 0;
            while(index < s.length() && Character.isDigit(s.charAt(index))){
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            numStack.push(num);
        }else{
            while(!oprtStack.isEmpty() && priorityMap.get(oprtStack.peek()) >= priorityMap.get(c)){
                // int num2 = numStack.pop();
                // int num1 = numStack.pop();
                // Character oprt = oprtStack.pop();
                // numStack.push(cal(num1, num2, oprt));
                process(numStack, oprtStack);
            }
            oprtStack.push(c);
            index++;


        }


    }

    while(!oprtStack.isEmpty()){
        // int num2 = numStack.pop();
        // int num1 = numStack.pop();
        // char oprt = oprtStack.pop();
        // numStack.push(cal(num1, num2, oprt));
        process(numStack, oprtStack);

    }


    return numStack.peek();



}

    private void process(Stack<Integer> numStack, Stack<Character> oprtStack){
        int num2 = numStack.pop();
        int num1 = numStack.pop();
        char oprt = oprtStack.pop();
        numStack.push(cal(num1, num2, oprt));

    }



    private int cal(int num1, int num2, char oprt){
        switch(oprt){
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
            case '/' : return num1 / num2;
        }
        return 0;
    }
}
