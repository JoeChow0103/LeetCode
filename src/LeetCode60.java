  import java.util.LinkedList;
  import java.util.List;

  public class LeetCode60 {
    public static String getPermutation(int n, int k) {
        // nums store the num to pick with order 1,2,3,...
        List<Integer> nums = new LinkedList<>();
        // factorials store the (n-1)! for calculate the idx latter
        int factorials[] = new int[n];

        factorials[0] = 1;
        nums.add(1);
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
            nums.add(i + 1);
        }

        k--; // k minus one in order to match the factorials
        // StringBuilder store the num picked
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            // calculate the idx of the num to pick
            int idx = k / factorials[i];
            // update the k for the next num to choose
            k -= idx * factorials[i];

            sb.append(nums.get(idx));
            nums.remove(idx);
        }

        return sb.toString();
    }

      public static void main(String[] args) {
          System.out.println(getPermutation(2, 2));
      }
}
