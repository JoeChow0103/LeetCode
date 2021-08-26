import java.util.HashMap;

public class LeetCode76 {
    /*
    final answer: [start, start + minLen]
    step1: store the t's char with int[] dictT
    step2: use slow&fast pointer, to find the window
    step3: move the fast, when find the all char in t,
    step4: move the slow, to shrink the head, and update start and minLen
    step5: when move the slow, if meet the char in t, put back to dicT
    step6: find the every substring contains t, and update minLen,finally get start and minLen
     */
    public String minWindow(String s, String t) {
        int[] dictT = new int[128];
        for (int i = 0; i < t.length(); i++){
            dictT[t.charAt(i)]++;
        }

        int slow = 0, minLen = s.length() + 1, start = 0, required = t.length();
        for (int fast = 0; fast < s.length(); fast++){
            char cur = s.charAt(fast);
            int count = dictT[cur];
            if (count > 0) {
                required--;
            }
            dictT[cur]--;


            while (required == 0){
                if (fast - slow + 1 < minLen){// find the substring contain t with minLen
                    minLen = fast - slow + 1;
                    start = slow;
                }

                cur = s.charAt(slow);
                count = dictT[cur];
                if (count == 0) required++;
                dictT[cur]++;
                slow++;

            }
        }
        return minLen == s.length() + 1 ? "" : s.substring(start, start + minLen);
    }
}
