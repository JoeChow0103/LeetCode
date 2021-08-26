import java.util.*;

public class LeetCode117 {
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
