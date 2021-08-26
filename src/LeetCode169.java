public class LeetCode169 {
    /*
    if it has no major?
    solution 1: traverse, traverse, two loop, Time: O(n^2), Space: O(1)
    solution 2: hashmap to improve the traverse, Time: O(n), Space: O(n)
    solution 3: one variable to count, if the num[i] == num[i - 1], count++;
    otherwise, count--. update the res when count == 1. traverse to the end.
    Because the len of major is no less than len/2, which is bigger than any other.
    But cannot solve the no major case.Time: O(n), Space: O(1)
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
