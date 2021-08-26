public class LeetCode287 {
    /*
    check cycle, from 1 to n, if no duplicate each number and index can be
    a linked list with no cycle.
    p1: why fast pointer is twice speed of slow pointer
    let's say when slow and fast in circle, slow fast need to t to meet.
    t = n * circle / (v2 - v1), to be efficient n is 1 which means
    within one circle fast meet slow and slow traverse less than a circle, then
    v1 * t <= circle, then 2v1 <= v2, then use twice speed.
    p2: why slow start with head, fast start with intersection, they will meet in
    the gate of circle.
    slow: 2*(a+b), fast: k*(b+c), a is no repeat, b is from the gate to intersection,
    and c is the rest of circle. slow * 2 = fast, we get
    a = (k-1)(b+c) + c = (k-1)*circle + c, which means they meet on the gate
    Time: O(n), Space: O(1)
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];

        }
        slow = 0;
        while(nums[slow] != nums[fast]){
            slow = nums[slow];
            fast = nums[fast];

        }

        return nums[slow];
    }
}
