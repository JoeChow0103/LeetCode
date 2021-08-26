import java.util.*;

public class LeetCode229 {
    /*
    solution 3: keep variables count1, count2, and candi1 and candi2 to store,
    can1, can2 initial； if num == can1, count1++, if num != can1 && num == can2, continue;
    else count1--. The same thing for can2. Traverse to the end, if count hit len/3,
    is a major.
    In algo, it's like to find first num with biggest len, and then second num with second
    biggest len. Then check the len if bigger than len/3, it's major.
    Time: O(n), Space: O(1)
     */

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }

        Integer num1 = null;
        Integer num2 = null;
        int c1 = 0;
        int c2 = 0;

        for(int num : nums){
            if(num1 == null || num1 == num){
                num1 = num;
                c1++;

            }else if(num2 == null || num2 == num){
                num2 = num;
                c2++;
            }else if(c1 == 0){
                num1 = num;
                c1++;
            }else if(c2 == 0){
                num2 = num;
                c2++;
            }else{
                c1--;
                c2--;
            }
        }
        int count = 0;
        if(num1 != null){
            for(int num : nums){
                if(num == num1){
                    count++;
                }
            }
            if(count > nums.length / 3){
                res.add(num1);
            }
        }

        count = 0;
        if(num2 != null){
            for(int num : nums){
                if(num == num2){
                    count++;
                }
            }
            if(count > nums.length / 3){
                res.add(num2);
            }
        }



        return res;
    }
}
