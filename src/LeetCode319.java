public class LeetCode319 {
    public static int bulbSwitch(int n) {
        int count = 0;
        for (int i = 1; i*i <= n; i++) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(bulbSwitch(3));
    }
}
