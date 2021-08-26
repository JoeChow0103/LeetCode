import java.util.HashMap;

public class LeetCode290 {
    public boolean wordPattern(String pattern, String str) {
        String[]arr = str.split("\\s+");
        HashMap<Character,String> map = new HashMap<>();
        if(pattern.length() == 0 && arr.length == 0){
            return true;
        }
        if(pattern.length() == 0 || arr.length == 0){
            return false;
        }
        if(pattern.length() != arr.length){
            return false;
        }
        for(int i = 0;i < pattern.length(); i++){
            char key = pattern.charAt(i);
            if(map.containsKey(key)){
                if(!map.get(key).equals(arr[i])){
                    return false;
                }
            }
            else{
                if(map.containsValue(arr[i])){
                    return false;
                }
                map.put(key,arr[i]);
            }
        }
        return true;
    }
}
