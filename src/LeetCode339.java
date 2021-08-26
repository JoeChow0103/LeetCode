import java.util.*;

public class LeetCode339 {
    private interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public void setInteger(int value);

        public void add(NestedInteger ni);

        public List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0, level = 1;
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while (!queue.isEmpty()) {
            int levelSum = 0, size = queue.size();
            while (size-- > 0) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                } else {
                    for (NestedInteger ni : cur.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            sum += levelSum * level++;
        }
        return sum;
    }
}
