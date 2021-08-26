import java.util.*;

class Node {
      int val;
      Node left;
      Node right;
      Node random;
      Node() {}
      Node(int val) { this.val = val; }
      Node(int val, Node left, Node right, Node random) {
          this.val = val;
          this.left = left;
          this.right = right;
          this.random = random;
      }
}

public class LeetCode1485 {
    private HashMap<Node, Node> map = new HashMap<>();
    public Node copyRandomBinaryTree(Node root) {
        // cc
        if (root == null) return null;
        return helper(root, new HashMap<>());
    }

    private Node helper(Node node, Map<Node, Node> map) {
        // c.c.
        if (node == null) {
            return null;
        }
        Node copy = new Node(node.val);
        if (!map.containsKey(node)) {
            map.put(node, copy);
        } else {
            return map.get(node);
        }
        copy.left = helper(node.left, map);
        copy.right = helper(node.right, map);
        copy.random = helper(node.random, map);
        return copy;
    }
}
