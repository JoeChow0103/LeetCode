import java.util.ArrayList;
import java.util.List;

public class LeetCode894 {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        for (int i = 0; i < n - 1; i++) {
            List<TreeNode> leftList = allPossibleFBT(i);
            List<TreeNode> rightList = allPossibleFBT(n - i - 1);

            for (TreeNode lNode : leftList) {
                for (TreeNode rNode : rightList) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = lNode;
                    cur.right = rNode;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
