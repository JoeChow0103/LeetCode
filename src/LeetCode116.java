import java.util.*;

class BNode {
    public int val;
    public BNode left;
    public BNode right;
    public BNode next;

    public BNode() {}

    public BNode(int _val) {
        val = _val;
    }

    public BNode(int _val, BNode _left, BNode _right, BNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class LeetCode116 {
    /*
    level order traverse, update the lchild.next, ignore the rchild.next. Record the lchild, for the update the lchild.next
     */
    public BNode connect(BNode root) {
        if (root == null) return root;
        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);
        BNode prev = null;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                BNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }

                if (prev != null) prev.next = cur;
                prev = cur;
            }
            prev = null;
        }
        return root;
    }
}
