import java.util.*;

public class LeetCode488 {
    int globalMin;
    public int findMinStep(String board, String hand) {
        if(board == null || hand == null) return -1;
        HashMap<Character, Integer> ball = new HashMap<>();
        for(int i = 0; i < hand.length(); i++){
            char ch = hand.charAt(i);
            ball.put(ch, ball.getOrDefault(ch, 0) + 1);
        }
        globalMin = hand.length() + 1;
        dfs(board, ball, 0);
        return globalMin == hand.length() + 1 ? -1 : globalMin;
    }

    private void dfs(String board, HashMap<Character, Integer> ball, int usedBall){
        if(board.isEmpty()){
            globalMin = Math.min(globalMin, usedBall);
            return;
        }
        if(ball.isEmpty() && !board.isEmpty()){
            return;
        }
        for(int i = 0; i < board.length(); i++){
            char ch = board.charAt(i);
            Integer cnt = ball.get(ch);
            if(cnt == null) continue;
            if(i + 1 < board.length() && board.charAt(i + 1) == ch){
                String newBoard = generate(board, i - 1, i + 2);
                int newcnt = cnt - 1;
                if(newcnt == 0){
                    ball.remove(ch);
                }else{
                    ball.put(ch, newcnt);
                }
                dfs(newBoard, ball, usedBall + 1);
                ball.put(ch, cnt);
                i++;
            }else if(cnt >= 2){
                String newBoard = generate(board, i - 1, i + 1);
                int newcnt = cnt - 2;
                if(newcnt == 0){
                    ball.remove(ch);
                }else{
                    ball.put(ch, newcnt);
                }
                dfs(newBoard, ball, usedBall + 2);
                ball.put(ch, cnt);
            }
        }
    }

    private String generate(String board, int left, int right){
        while(left >= 0 && right < board.length()){
            char ch = board.charAt(left);
            int i = left;
            int cnt = 0;
            while(i >= 0 && board.charAt(i) == ch){
                cnt++;
                i--;
            }
            int j = right;
            while(j < board.length() && board.charAt(j) == ch){
                cnt++;
                j++;
            }
            if(cnt < 3){
                break;
            }else{
                left = i;
                right = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x <= left; x++){
            sb.append(board.charAt(x));
        }
        for(int y = right; y < board.length(); y++){
            sb.append(board.charAt(y));
        }
        return sb.toString();
    }
}
