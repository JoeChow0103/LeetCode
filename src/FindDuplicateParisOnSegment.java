import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FindDuplicateParisOnSegment { //
    /*
    01010
    0:3, 1:2
     */
    int duplicateParisOnSegment(int[] numbers, int k) {
        int len = numbers.length, p1 = 0, p2 = 1;
        int start = 0, end = 0;
        int count = 1, max = Integer.MIN_VALUE;
        while (p2 + 2 < len) {
            if (numbers[p1] == numbers[p1 + 2] && numbers[p2] == numbers[p2 + 2]) {
                count++;
                max = Math.max(count, max);
                p1 = p1 + 2;
                p2 = p2 + 2;
            } else if (numbers[p1] == numbers[p1 + 2]){
                count++;
                max = Math.max(count, max);
                p1++;
                p2++;
            } else {
                p1++;
                p2++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        FindDuplicateParisOnSegment solution = new FindDuplicateParisOnSegment();
        int[] numbers = {0,1,0,1,0};
        int max = solution.duplicateParisOnSegment(numbers, 2);
        System.out.println(max);
    }
}
