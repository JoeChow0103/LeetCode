import java.util.*;

public class LeetCode239 {
    /*
    S1: use queue or two pointers, for every possible position, iterate the elements we can see (inside the window scope) to find max
    Time (n - k + 1) * k
    S2: use a maxheap to optimize the process to find max ? → MyHeap
    (n - k + 1) * logk
    S3: use descending deque, the leftmost element is always the max of the current sliding window.
    *** why deque: after demo, need O(1) time to remove/insert from first and last
    step1: compare the left most element's value of the deque with the element on the leftmost index of the sliding
    step2: from the right to left of the sliding window, if x > curTail, remove curTail repeatedly, put x into the sliding window
    step3: the leftmost elment is the max value of current sliding window.
    n * 1
    Time: O(n), amortized, every element in and out O(1), then O(n)
    1 3 -1 -3 5 3 6 7
    -3 -1 3
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*
        1,3,-1,-3,5,3,6,7
        dq: 1 | 3 | -1 3 | -1 3 | -1(middle) | 5 | 3 5 | 6 5 | 6(middle) | 7 6
        */
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            while(!dq.isEmpty() && dq.peekFirst() < nums[i]){
                dq.pollFirst();
            }
            dq.offerFirst(nums[i]);
            if(i >= k && dq.peekLast() == nums[i - k]){
                dq.pollLast();
            }

            if(i >= k - 1){
                res[i - k + 1] = dq.peekLast();
            }
        }
        return res;

    }
}
