public class LeetCode245 {
    /*
    if word1 == word2, maintain idx1, idx2 to store the positions,
    update the idx1 idx2 according to case to guarantee they won't
    update at the same time. Ex. if (i1 < i2) update i1; else i2.
     */
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int len = wordsDict.length;
        int i1 = -1, i2 = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            String s = wordsDict[i];
            if (word1.equals(word2) && s.equals(word1)) {
                if (i1 == -1) {
                    i1 = i;
                } else if (i2 == -1) {
                    i2 = i;
                } else {
                    if (i1 < i2) {
                        i1 = i;
                    } else {
                        i2 = i;
                    }
                }
            } else {
                if (s.equals(word1)) {
                    i1 = i;
                } else if (s.equals(word2)) {
                    i2 = i;
                }
            }
            if (i1 != -1 && i2 != -1) {
                min = Math.min(Math.abs(i1 - i2), min);
            }
        }
        return min;
    }
}
