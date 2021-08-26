import java.util.*;

public class LeetCode257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), root);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, TreeNode root) {
        if (root == null) return;
        int len = sb.length();

        if (root.left == null && root.right == null) {
            sb.append(root.val);
            res.add(sb.toString());
        } else {
            sb.append(root.val + "->");
            dfs(res, sb, root.left);
            dfs(res, sb, root.right);
        }

        sb.setLength(len);
    }
}
