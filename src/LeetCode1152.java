import java.util.*;

public class LeetCode1152 {
    class Log {
        int timeStamp;
        String website;

        public Log(int timeStamp, String website) {
            this.timeStamp = timeStamp;
            this.website = website;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        // store <username, log>
        HashMap<String, List> map = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            if (!map.containsKey(username[i])) {
                map.put(username[i], new ArrayList());
            }
            map.get(username[i]).add(new Log(timestamp[i], website[i]));
        }

        // for all possible combination, store the count
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String key : map.keySet()) {
            List<Log> list = map.get(key);
            Collections.sort(list, (a, b) -> a.timeStamp - b.timeStamp);
            HashSet<String> set = new HashSet<>();
            dfs(list, 0, new StringBuilder(), 0, set);
            for (String seq : set) {
                countMap.put(seq, countMap.getOrDefault(seq, 0) + 1);
            }
        }

        // get the pattern has the biggest scores in alphabet
        List<String> seqList = new ArrayList<>();
        for (String key : countMap.keySet()) {
            seqList.add(key);
        }
        Collections.sort(seqList, (a, b) -> countMap.get(a) == countMap.get(b)
                ? b.compareTo(a)
                : countMap.get(a) - countMap.get(b));
        String longestSeq = seqList.get(seqList.size() - 1);
        String[] arr = longestSeq.split(",");
        for (String seq : arr) {
            res.add(seq);
        }
        return res;
    }

    private void dfs(List<Log> input, int index, StringBuilder sb, int count, Set<String> res){
        if(count == 3){
            sb.deleteCharAt(sb.length() - 1);
            String seq = String.valueOf(sb);
            res.add(String.valueOf(sb));
            return;
        }

        int length = sb.length();
        for(int i = index; i < input.size(); i++){
            sb.append(input.get(i).website).append(",");
            dfs(input, i + 1, sb, count + 1, res);
            sb.setLength(length);
        }
    }
}
