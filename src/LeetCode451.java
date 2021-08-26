import java.util.*;

public class LeetCode451 {
    public String frequencySort(String s) {
        if(s == null || s.length() <= 2) return s;

        //Create HashMap to store the key value pair
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //Sort with heap
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>(){
            @Override
            public int compare(Character c1, Character c2){
                return map.get(c2) - map.get(c1);
                // if(map.get(c1) == map.get(c2)){
                //     return c1 - c2;
                // }
                // return map.get(c2) - map.get(c1);
            }
        });

        StringBuilder sb = new StringBuilder();

        for(Character c : map.keySet()){
            pq.offer(c);
        }

        while(!pq.isEmpty()){
            Character c = pq.poll();
            int count = map.get(c);
            while(count > 0){
                sb.append(c);
                count--;
            }
        }
        return sb.toString();
    }
}
