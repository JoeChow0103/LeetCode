public class LeetCode19 {
    /*
    demo, use count to find the node, keep prev to do prev.next = cur.next
    two path, one path to get the len, one path to remove
    Time: O(n)
    improve: slow fast, keep distance of k - 1
    corner case: remove head, dummy node
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // cc
        if (head == null || head.next == null) return null;
        ListNode dummy = new ListNode(0); // dummy 处理corner case：[1], n=1;
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        for (int i = n; i > 0; i--) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

//    public ListNode removeNthFromEnd(ListNode Head, int n) { // 不用dummy
//        // cc
//        if (head == null || head.next == null) return null;
//        ListNode slow = head, fast = head;
//        for (int i = n; i > 0; i--) {
//            fast = fast.next;
//        }
//        if (fast == null) return head.next; // check cc: [1], n=1;
//        while (fast.next != null) {
//            slow = slow.next;
//            fast = fast.next;
//        }
//        slow.next = slow.next.next;
//        return head;
//    }

}
