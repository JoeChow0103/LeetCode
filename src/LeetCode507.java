public class LeetCode507 {
    /*
    brute force, Time: O(n), Space: O(1)
    improve with sqrt(num), Time: O(sqrt(n)), Space: O(1)
     */
    public boolean checkPerfectNumber(int num) {
//         if (num <= 0) {
//             return false;
//         }
//         int sum = 0;
//         for (int i = 1; i <= (int) num/2; i++) {
//             if (num % i == 0) {
//                 sum += i;
//             }

//         }
//         return sum == num;

        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }
}
