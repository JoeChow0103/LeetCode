public class LeetCode12 {
    /*
    Greedy or Table 0~9. Because traver the vals, then Time: O(1), Space: O(1);
     */
    public String intToRoman(int num) {
        int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (num > 0) {
            int k = num / vals[i];
            for (int j = 0; j < k; j++) {
                sb.append(romans[i]);
                num -= vals[i];
            }
            i++;
        }
        return sb.toString();
    }
}
