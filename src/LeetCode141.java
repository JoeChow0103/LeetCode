import java.util.*;

public class LeetCode141 {
    /*
    sol1: slow & fast
    sol2: hashmap
    LC287
     */
    public boolean hasCycle(ListNode head) {
        // cc
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        // single node
        // if (fast == null) return false; 下面的fast.next != null已经check了只有一个node的情况
        // bc
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

//    public boolean hasCycle(ListNode head) {
//        Set<ListNode> visited = new HashSet<>():
//        while (head != null) {
//            if (visited.contains(head)) {
//                return true;
//            } else {
//                visited.add(head);
//            }
//            head = head.next;
//        }
//        return false;
//    }

}
