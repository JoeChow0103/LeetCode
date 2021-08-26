import java.util.*;

public class LeetCode216 {
    /*
    idea:
    1. pick a number and put into a list: curList
    2. check sum of the list is smaller or equal to n.
        if yes, then continue;
        if no, then break;
    3. check number of the list is smaller of equal to k.
        if yes, then continue;
        if no, then break;
    Step 1 - 3 if a precedure of a recursion,
    for every time pick the number starting from the picked number, then
    don't need to worry the duplicated pick a number.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void helper(int num, int remainVal, int remainCount, List<Integer> curList, List<List<Integer>> result) {
        // base case
        if (remainCount == 0 && remainVal == 0) { // success
            result.add(new ArrayList<>(curList));
            return;
        }
        if (remainVal < 0 || remainCount <= 0) return; // failure

        for (int i = num; i <= 9; i++) {
            curList.add(i);
            helper(i + 1, remainVal - i, remainCount - 1, curList, result);
            curList.remove(curList.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode216 solution = new LeetCode216();
        int k = 2, n = 5;
        System.out.println(solution.combinationSum3(k, n));
    }
}
