public class LeetCode160 {
    /*
    a+c+b = b+c+a
    191241912419124
    324324324324324

    19124324
    32419124
     */
    public ListNode getIntersetctionNode(ListNode headA, ListNode headB) {
        // cc
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB;
            p2 = p2 != null ? p2.next : headA;
        }
        return p1;
    }
}
