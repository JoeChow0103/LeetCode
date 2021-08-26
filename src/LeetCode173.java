import java.util.*;

public class LeetCode173 {
}

class BSTIterator {
    /*
    the tree traverses with in-order. Then use a queue to store the node.val with in-order.
    While the queue is not empty, the still has number in it. Get the node.val with O(1);
    Time: O(n), Space: (n)
     */
    Queue<Integer> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        inorder(root, queue);
    }

    public int next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void inorder(TreeNode cur, Queue<Integer> queue) {
        if (cur == null) {
            return;
        }
        inorder(cur.left, queue);
        queue.offer(cur.val);
        inorder(cur.right, queue);
    }
}