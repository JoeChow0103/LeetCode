import java.util.ArrayList;
import java.util.List;

public class LintCode11 {
    /*
    search range in BST, return a bst in range(k1, k2)
    if root.val > k1, search left tree
    if root.val < k2, search right tree
    if root.val belong [k1, k2], add root
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        print(res, root, k1, k2);
        return res;
    }

    private void print (List<Integer> res, TreeNode root, int k1, int k2) {
        if (root == null) return;
        if (k1 < root.val) print(res, root.left, k1, k2);
        if (k1 <= root.val && root.val <= k2) res.add(root.val);
        if (k2 > root.val) print(res, root.right, k1, k2);
    }
}
