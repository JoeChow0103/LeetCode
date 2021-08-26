import java.util.*;

public class LeetCode105 {
    /*
    preOrder find the root in the first element, then in inOrder, left of the root is the inOrder of the left tree with size n,
    right of the root is the inOrder fo the right tree with size m.
    The subarray right from the root with the size n is preOrder of the left tree.
    The subarray right from the left tree witht he size m is preOrder of the right tree.
     */
//    private TreeNode construct(int[] pre, int[] in, int preLeft, int preRight, int inLeft, int inRight) {
//        if (preLeft > preRight) return null;
//        int rootVal = pre[preLeft];
//        int rootIdx = findIdx(in, rootVal); // binary or linear: logN or N
//        TreeNode root = new TreeNode(rootVal);
//        root.left = construct(pre, in, preLeft + 1, rootIdx - inLeft + preLeft, rootIdx + 1, inRight);
//        root.right = construct(pre, in, rootIdx + preLeft + 1 - inLeft, preRight, rootIdx + 1, inRight);
//        return root;
//    }
//
//    private int findIdx(int[] in, int rootVal) {
//        return -1;
//    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = preorder.length;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, len - 1, inorder, 0, len - 1, map);
        return root;
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        if (preStart > preEnd) return null;
        int rootVal = preorder[preStart];
        int rootIdx = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart + 1, rootIdx - inStart + preStart, inorder, inStart, rootIdx - 1, map);
        root.right = buildTree(preorder, rootIdx + preStart + 1 - inStart, preEnd, inorder, rootIdx + 1, inEnd, map);
        return root;
    }

}
