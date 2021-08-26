public class LeetCode61 {
    /*
    make a cycle, then count the cur make it as new tail, make cur.next as new head
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }

        ListNode cur = head;
        int count = 1;
        while(cur != null && cur.next != null){
            cur = cur.next;
            count++;
        }

        k %= count;
        cur.next = head;

        cur = head;
        for(int i = 0; i < count - k - 1; i++){
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }
}
