public class LeetCode485 {
    /*
    Hence if one pig has two available states, x pigs could test 2^x buckets.
    states = minutesToTest / minutesToDie + 1
    x >= log buckets/log states
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }
}
