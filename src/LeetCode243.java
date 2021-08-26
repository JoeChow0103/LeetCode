public class LeetCode243 {
    public int shortestDistance(String[] words, String word1, String word2) {
        int ret = Integer.MAX_VALUE;
        Integer index1 = null;
        Integer index2 = null;
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            if(word.equals(word1)){
                index1 = i;
            }else if(word.equals(word2)){
                index2 = i;
            }
            if(index1 != null && index2 != null){
                ret = Math.min(ret, Math.abs(index1 - index2));
            }

        }
        return ret;
    }
}
