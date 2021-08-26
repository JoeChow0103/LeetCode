public class LeetCode988 {
    /*
    traverse the tree by recursion, and record the every path from leafnode to root. Compare the paths
     */
    String ans = "~"; // dummy value '~' > 'z', ASCII
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append((char)('a'+ root.val));
        if (root.left == null && root.right == null) {
            sb.reverse();
            String s = sb.toString();
            sb.reverse();

            if (s.compareTo(ans) < 0) {
                ans = s;
            }

        }
        dfs(root.left, sb);
        dfs(root.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(s.compareTo("~"));
    }
}
