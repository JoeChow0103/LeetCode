import java.util.*;

public class LeetCode428 {
}

class Codec {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        helper_serialize(root, sb);
        sb.setLength(sb.length() - 1);  // remove last ","
        return sb.toString();
    }
    private void helper_serialize(Node root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        sb.append(root.children.size()).append(",");
        for (Node next : root.children) {
            helper_serialize(next, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String s : arr) {
            queue.offer(s);
        }
        return helper_deserialize(queue);
    }
    private Node helper_deserialize(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        String cur = queue.poll();
        String size = queue.poll();
        Node root = new Node(Integer.parseInt(cur));
        root.children = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(size); i++) {
            root.children.add(helper_deserialize(queue));
        }
        return root;
    }
}
