import java.util.ArrayList;
import java.util.List;

public class LeetCode1352 {
}

class ProductOfNumbers {
    List<Integer> nums = new ArrayList<>();
    int prev;

    public ProductOfNumbers() {
        nums.add(1);
        prev = 1;
    }

    public void add(int num) {
        if (num != 0) {
            prev *= num;
            nums.add(prev);
        } else {
            nums = new ArrayList<>();
            nums.add(1);
            prev = 1;
        }
    }

    public int getProduct(int k) {
        int size = nums.size();
        return (k < size) ? prev / nums.get(size - k - 1) : 0;
    }
}