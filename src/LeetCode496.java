import java.util.*;

public class LeetCode496 {
    /*
    For the brute force solution, traverse the first array from left to right. For each number in the array 1,
    find the position in the second array, then look right to find the first greater number.
    Time is O(m*n) m, n is the length. O(n) is the traverse time.
    There are redundancy. What if I can read the first greater number for each number in array 2.
    Then it will be faster. Use a hash map put the number as key and the first greater number as value.
    Use the stack to find the greater number. O(n)
    Time will be O(m) + O(n)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //cc
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums2.length; i++){
            while(!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while(!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }
        for(int i = 0; i < nums1.length; i++){
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
