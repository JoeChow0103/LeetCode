import com.util.TreeNode;

import java.util.*;

public class LeetCode297 {
    /**
     * Serialization is the process of converting a data structure or object into
     * a sequence of bits so that it can be stored in a file or memory buffer,
     * or transmitted across a network connection link to be reconstructed later in the same
     * or another computer environment.
     *
     * Serialization is a process of converting data structure or object into a form of bits which
     * machine can read and execute accordingly.
     * Deserialization is on contrast is translate the bits to programming language which we can read.
     *
     * In this case, we only do the serialization or deserialization in a simple way.
     * We will translate a binary tree to a string.
     *
     * Serialization: 1. traverse the binary tree; 2. transfer and restore the Integer to String
     * Deserialization: 1. read the Integer from String; 2. build a tree to store the Integers
     * Build a binary tree: we need treeNode, val. We can use a dfs build a tree because we need to travers every node.
     * It's intuitive. The dfs has two form: top down and bottom up. But this time we use top down.
     * Because we need to store the value by the order of the Integers from the input.
     * We store the value to the parent then we call its leftChild and rightChild until we call the base case.
     * The base case is when we traverse to the null below the leaf node.
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder(); // we want to modify without creating a new object
        traverse(root, sb); // preOrder traverse
        String res = sb.toString();
        return res.substring(1); // because in traverse, the first char is ",", which we don't need
    }

    private void traverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(",null");
            return;
        }
        sb.append("," + root.val);
        traverse(root.left, sb);
        traverse(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String str[] = data.split(",");
        List<String> list = new LinkedList<String>();
        for (String s : str) {
            list.add(s);
        }
        TreeNode root = buildTree(list);
        return root;
    }

    private TreeNode buildTree(List<String> list) {
        if (list == null || list.size() == 0) return null;
        String val = list.get(0);
        list.remove(0);
        if (val.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = buildTree(list);
        root.right = buildTree(list);
        return root;
    }

    public void DFS(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        System.out.println(root.val);
        DFS(root.left);
        DFS(root.right);
    }

    public static void main(String[] args) {
        LeetCode297 solution = new LeetCode297();
        String str = "1,2,3,null,null,4, 5";
        // leafNode
        TreeNode root = solution.deserialize(str);
        solution.DFS(root);

//        TreeNode treeNode = TreeNode.newTree(1, 2, 3, null, null, 4, 5);
//        String newStr = solution.serialize(treeNode);
//        System.out.println(newStr);
    }
}
