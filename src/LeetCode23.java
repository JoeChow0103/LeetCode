import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode23 {
    /*
    0. Clarify
    1. Approach: explain how and choose solution accordingly
    2. Function Signature: input / output / helper reference to pass through
    3. Assumption: corner case / edge case / base case
    4. Comment + Code + Review(Test cases)
    5. Time Complexity and Justify (intuitive or formula)
    6. Space Complexity Data Structure Selection and Justify

    Given k arrays, merge them into one big sorted array.
    0 type
    1 sorted vs unsorted
    2 ascending vs descending
    3 duplicate? every array / global
    4 size of array
    5 resource limit
    0.6 → positive or negative or 0


    solution1: binary divide, k lists, Time:O(n * k * logk), Space: O(n)
    divideMerge(int[k][], List<int[]>, ListNode[k], List<ListNode>)
    solution2: k pointers, who small pick who, k*n*?*k
    use Hashmap, Time:O(n*k*logk), Space:O(k)
    solution3: MapReduce
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode head = null;
        ListNode cur = null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(
                lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        }
        );

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode temp = minHeap.poll();
            if(head == null) {
                head = temp;
                cur = head;
            } else {
                cur.next = temp;
                cur = cur.next;
            }

            if (temp.next != null) {
                minHeap.offer(temp.next);
            }
        }
        return head;
    }
}
