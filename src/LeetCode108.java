public class LeetCode108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;

        return convert(nums, 0, nums.length - 1);
    }

    private TreeNode convert(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums, left, mid - 1);
        root.right = convert(nums, mid + 1, right);
        return root;
    }
}
