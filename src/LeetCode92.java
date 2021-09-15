public class LeetCode92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        for (int i = 1; i < left; i++) {
            cur = cur.next;
        }
        ListNode endPoint1 = cur;
        ListNode curHead = cur.next;
        for (int i = 0; i <= right - left; i++) {
            cur = cur.next;
        }
        ListNode curTail = cur;
        ListNode endPoint2 = cur.next;
        curTail.next = null;

        ListNode temp = reverseLinkedList(curHead);
        endPoint1.next = temp;
        curHead.next = endPoint2;

        return dummy.next;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null, cur = head, post = cur.next;

        while (cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }
}
