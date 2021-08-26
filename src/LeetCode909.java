import java.util.*;

public class LeetCode909 {
    /*
    construct a graph using hashmap, key is value, value is coord
    notice:
    1.the matrix is different, to get the coordinate check if the row is even
    2.two ways to out, jump to the end from another level, move on the same level;
    3.bfs, queue is level, when size is 0, then move to another level, step++;
    Time:  O(m*n), Space: O(m*n)
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        Map<Integer, List<Integer>> valueToPos = new HashMap<>();

        int k = 1;
        boolean flag = true;

        for(int i = n - 1; i >= 0; i--){
            if(flag){
                for(int j = 0; j < n; j++){
                    valueToPos.put(k++, Arrays.asList(i, j));
                }
                flag = false;
            }else{
                for(int j = n - 1; j >= 0; j--){
                    valueToPos.put(k++, Arrays.asList(i, j));
                }
                flag = true;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];

        int step = 1;

        while(!queue.isEmpty()){
            int size = queue.size();

            while(size-- > 0){
                int curVal = queue.poll();

                if(visited[curVal]) continue;
                visited[curVal] = true;

                for(int i = 1; i <= 6 && curVal + i <= n * n; i++){
                    int nextVal = curVal + i;

                    List<Integer> pos = valueToPos.get(nextVal);
                    int value = board[pos.get(0)][pos.get(1)];

                    if(value > 0) nextVal = value;

                    if(nextVal == n * n) return step;
                    if(!visited[nextVal]) queue.offer(nextVal);
                }
            }
            step++;
        }

        return -1;
    }
}
