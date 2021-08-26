import java.util.Arrays;

public class ConsolidatePartition {
    public static int consolidate(int[] used, int[] total) {
        int len = total.length;
        int usedSum = 0;
        int countTotal = 0;
        Arrays.sort(total);

        for (int memory: used) {
            usedSum += memory;
        }

        int currentTotal = 0;
        int partitions = 0;
        while (usedSum > 0) {
            currentTotal = total[len - countTotal - 1];
            usedSum -= currentTotal;

            if (usedSum > 0) {
                partitions++;
                countTotal++;
                continue;
            }
            partitions++;
        }
        return partitions;
    }

    public static void main(String[] args) {
        int[] usedSpace = {3,2,1,3,1};
        int[] totalSpace = {3,5,3,5,5};
        int partitions = consolidate(usedSpace, totalSpace);
        System.out.println(partitions);
    }
}
