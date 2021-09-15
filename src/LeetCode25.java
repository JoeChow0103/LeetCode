public class LeetCode25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = head;
        int count = 0;

        // find k size sublist
        while (pre != null && count < k) {
            pre = pre.next;
            count++;
        }

        // reverse k size sublist
        if (count == k) {
            pre = reverseKGroup(pre, k);
            ListNode cur = head, post = head.next;

            while (count-- > 0) {
                post = cur.next;
                cur.next = pre;
                pre = cur;
                cur = post;
            }
            head = pre;
        }
        return head;
    }
}
