public class LeetCode2 {
    /*
    demo: 999+99
    case1: n1&n2 != null
    case2: n1 | n2 != null
    carryover
    end case: carryover > 1
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode c1 = l1;
        ListNode c2 = l2;

        ListNode head = new ListNode((c1.val + c2.val) % 10);
        ListNode cur = head;
        int carryOver = (c1.val + c2.val) / 10;
        c1 = c1.next;
        c2 = c2.next;
        while(c1 != null && c2 != null){
            int sum = c1.val + c2.val + carryOver;
            cur.next = new ListNode(sum % 10);
            carryOver = sum / 10;
            cur = cur.next;
            c1 = c1.next;
            c2 = c2.next;
        }
        while(c1 != null){
            int sum = c1.val + carryOver;
            cur.next = new ListNode(sum % 10);
            carryOver = sum / 10;
            cur = cur.next;
            c1 = c1.next;
        }
        while(c2 != null){
            int sum = c2.val + carryOver;
            cur.next = new ListNode(sum % 10);
            carryOver = sum / 10;
            cur = cur.next;
            c2 = c2.next;
        }

        if(carryOver != 0){
            cur.next = new ListNode(carryOver);
        }
        return head;

    }
}
