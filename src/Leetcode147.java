public class Leetcode147 {
    /*
    insert sort
    */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;

        // each time we traverse from dummy to insert cur
        while (cur != null) {
            ListNode prev = dummy;
            // find a node with its next node's val is larger then cur's
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            }
            // insert the cur into the new linkedList
            ListNode next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            // update the cur to next
            cur = next;
        }
        return dummy.next;
    }
}
