import java.util.LinkedList;
import java.util.Queue;

public class LeetCode449 {
    class Codec {
        /**
         * transform from TreeNode to String;
         * TreeNode need to traverse, get the value of the node, get together and transform to String
         *
         * @param root
         * @return
         */
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // cc
            if (root == null) return null;
            StringBuilder sb = new StringBuilder();
            preOrder(root, sb);
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }

        private void preOrder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            int val = root.val;
            sb.append(String.valueOf(val));
            sb.append(",");
            preOrder(root.left, sb);
            preOrder(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) return null;
            String[] str = data.split(",");

            Queue<Integer> q = new LinkedList<>();
            for (String s : str) {
                q.offer(Integer.valueOf(s));
            }

            return helper(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode helper(Queue<Integer> q, int lower, int upper) { // bound
            if (q == null || q.isEmpty()) return null;

            int val = q.peek();
            if (val <= lower || val >= upper) { // no leaf node
                return null;
            }

            TreeNode root = new TreeNode(q.poll());
            root.left = helper(q, lower, root.val);
            root.right = helper(q, root.val, upper);

            return root;
        }
    }
}
