public class LeetCode445 {
    /*
    reverse then add
    Time: O(n+m), Space: O(n+m)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseHead1 = reverse(l1);
        ListNode reverseHead2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while(reverseHead1 != null && reverseHead2 != null) {
            int sum = reverseHead1.val + reverseHead2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            reverseHead1 = reverseHead1.next;
            reverseHead2 = reverseHead2.next;
        }

        while(reverseHead1 != null) {
            int sum = reverseHead1.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            reverseHead1 = reverseHead1.next;
        }

        while(reverseHead2 != null) {
            int sum = reverseHead2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            reverseHead2 = reverseHead2.next;
        }

        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        // cc
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
