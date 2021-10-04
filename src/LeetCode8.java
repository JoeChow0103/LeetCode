public class LeetCode8 {
    /*
    divide and conquer. Step by step.
     */
    public int myAtoi(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int num = 0;
        Integer sign = null;

        boolean seenNum = false;

        int index = 0;
        // remove the extra space
        while(index < s.length() && s.charAt(index) == ' '){
            index++;
        }
        while(index < s.length()){
            char c = s.charAt(index);
            if(c == '+'){
                if(sign != null || seenNum){
                    break;
                }
                sign = 1;
            }else if(c == '-'){
                if(sign != null || seenNum){
                    break;
                }
                sign = -1;

            }else if(Character.isDigit(c)){
                seenNum = true;
                int temp = c - '0';

                if(sign == null || sign == 1){
                    if(num > (Integer.MAX_VALUE - temp) / 10){
                        return Integer.MAX_VALUE;
                    }else{
                        num = num * 10 + temp;
                    }

                }else{
                    if(num < (Integer.MIN_VALUE + temp) / 10){
                        return Integer.MIN_VALUE;
                    }else{
                        num = num * 10 - temp;
                    }

                }
            }else{
                break;
            }
            index++;

        }


        return num;


    }
}
