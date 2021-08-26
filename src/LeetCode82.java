public class LeetCode82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (prev.next == cur) {
                prev = cur;
            } else {
                prev.next = cur.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
