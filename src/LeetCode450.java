public class LeetCode450 {
    /**
     * A classical case for find the parent of a child
     * case 1: root.val == target
     * 	case a:  if root has both lchild and rchild,  find smallest element from right subtree( or find the largest element from the left subtree), say x, assign x's value to root, remove x's value from the right subtree by recursion
     * 	case b: if root has only one child, iff has one child, replace root with non-null child / parent connect directly to child,
     * 	case c: if root has no child, remove self / root = null
     *
     * case 2: root.val > target, go to left subtree by recursion
     *
     * case 3: root.val < target, go to right subtree by recursion
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (root.val == key) {
            // case a
            if (root.left != null && root.right != null) {
                root.val = findMin(root.right).val;
                root.right = deleteNode(root.right, root.val);
            } else {
                root = root.left != null ? root.left : root.right;
            }
        } else if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode findMin(TreeNode root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }
}
