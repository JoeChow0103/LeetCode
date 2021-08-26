public class LeetCode426 {
    /*
    TreeNode {
        int value;
        TreeNode left; // prev
        TreeNode right; // next
    }
    inplace: prev.right = root; root.left = prev
    null  3 ⇔ 5 ⇔ 16 ⇔ 15 ⇔ 18 ⇔ 20 ⇔ 23 ⇔ null
                                      prev cur
    to the end, prev is the tail
     */
    private TreeNode prev = null;
    private TreeNode head = null;
    // private TreeNode tail = null;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        inOrder(root);
        return head;
    }
    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        // do sth
        if (prev != null) prev.right = root;
        else head = root;
        root.left = prev;

        prev = root;
        // tail = root;
        inOrder(root.right);
    }
}
