import java.util.Arrays;

public class LeetCode179 {
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strArr = new String[len];
        for (int i = 0; i < len; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strArr, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));

        if (strArr[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            sb.append(s);
        }
        return sb.toString();
    }
}
