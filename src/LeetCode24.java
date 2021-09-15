public class LeetCode24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        ListNode prev = dummy;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next.next;
            prev.next = cur.next;
            prev.next.next = cur;
            prev = cur;
            cur = next;
        }
        prev.next = cur;
        return dummy.next;
    }
}
