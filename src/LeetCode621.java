import java.util.*;

public class LeetCode621 {
    /*
    sort the tasks by numbers in descending order, and fulfill as many idle slots as possible
    First consider the most frequent characters, we can determine their positions first and use them as a frame
    to insert the remaining less frequent characters. Here is a proof by construction:

Let F be the set of most frequent chars with frequency k.
Then we can create k chunks, each chunk is identical and is a string consists of chars in F in a specific fixed order.
Let the heads of these chunks to be H_i, then H_2 should be at least n chars away from H_1, and so on so forth;
then we insert the less frequent chars into the gaps between these chunks sequentially one by one ordered by frequency
in a decreasing order and try to fill the k-1 gaps as full or evenly as possible each time you insert a character.

In summary, append the less frequent characters to the end of each chunk of the first k-1 chunks sequentially and round
and round, then join the chunks and keep their relative distance from each other to be at least n.
    AAABBBCCCD k = 1
    ABACBCACBD
     */
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t -'A']++;
        }

        Arrays.sort(frequencies);

        // max frequency
        int f_max = frequencies[25];
        System.out.println(f_max);
        int idle_time = (f_max - 1) * n;
        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]);
        }
        idle_time = Math.max(0, idle_time);
        return idle_time + tasks.length;
    }

    public static void main(String[] args) {
        LeetCode621 solution = new LeetCode621();
        char[] tasks = new char[] {'A','A','A','C','D','E'};
        int n = 2;
        int res = solution.leastInterval(tasks, n);
        System.out.println(res);
    }
}
