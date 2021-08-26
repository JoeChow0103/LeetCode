public class LeetCode165 {
    /*
    for each string, split by ".", then convert all char to number to get a number array.
    Then we have two number array, one path from left to right, compare the number.
    If 1 is larger then return 1, if 2 is larger then return 2. To the end, return 0;
    The two array may have different lengths. Then if point surpass the shorter length,
    then the number is 0;
    Time: O(n), Space(n - k), k number of dots
     */
    public int compareVersion(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        for (int i = 0; i < Math.max(strs1.length, strs2.length); i++) {
            int val1 = i >= strs1.length ? 0 : Integer.valueOf(strs1[i]);
            int val2 = i >= strs2.length ? 0 : Integer.valueOf(strs2[i]);
            if (val1 == val2) {
                continue;
            } else if (val1 < val2) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
