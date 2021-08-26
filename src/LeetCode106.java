import java.util.HashMap;

public class LeetCode106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = postorder.length;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(inorder, 0, len - 1, postorder, 0, len - 1, map);
        return root;
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        int rootIdx = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder, inStart, rootIdx - 1, postorder, postStart, rootIdx - inStart + postStart - 1, map);
        root.right = buildTree(inorder, rootIdx + 1, inEnd, postorder, rootIdx - inEnd + postEnd, postEnd - 1, map);
        return root;
    }
}
