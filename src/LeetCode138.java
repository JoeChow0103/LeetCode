import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode random;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class LeetCode138 {
    public ListNode copyRandomList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode cur1 = head;
        ListNode cur2 = new ListNode(0);
        cur2.next = head;
        HashMap<ListNode, ListNode> map = new HashMap<>();

        while (cur1 != null) {
            if (!map.containsKey(cur1)) {
                map.put(cur1, new ListNode(cur1.val));
            }
            cur2.next = map.get(cur1);
            //
            if (cur1.random != null) {
                if (!map.containsKey(cur1.random)) {
                    map.put(cur1.random, new ListNode(cur1.random.val));
                }
                cur2.next.random = map.get(cur1.random);
            }

            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return map.get(head);
    }
}
