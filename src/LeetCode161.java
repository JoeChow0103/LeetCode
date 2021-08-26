public class LeetCode161 {
    /*
    insert/delete |len_s - len_t| = 1, lengths are different by one,
    replace, lengths are the same.
    insert/delete is to reuse the method
    Time: O(n), Space: O(N), substring;
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        if(ns > nt){
            return isOneEditDistance(t, s);
        }
        for(int i = 0; i < ns; i++){
            if(s.charAt(i) != t.charAt(i)){
                if(ns == nt){
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }else{
                    return s.substring(i).equals(t.substring(i + 1));
                }

            }

        }
        return (ns + 1 == nt);
    }
}
