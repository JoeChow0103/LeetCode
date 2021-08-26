public class LeetCode206 {
    /*
    solution1: while loop
    solution2: recursion, create new connection and break the old connection
     */
    public ListNode reverseList(ListNode head) {
//        // cc
//        if (head == null || head.next == null) return head;
//        ListNode cur = head, next = null, pre = null;
//        while (cur != null) {
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;

        // cc
        if (head == null || head.next == null) return head;
        ListNode newHead= reverseList(head.next); // call到最后，返回的是node6
        head.next.next = head;
        head.next = null;
        return newHead; //return上来的是node6
    }

}
