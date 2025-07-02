package strings;

public class LexicographicalSmallestString {
    /*
    https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
     */

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] charIdx = new int[26]; //To store each character minimum index.
        for(int i = 0; i < 26; i++) {
            charIdx[i] = i;
        }
        int len = baseStr.length();

        /*
        In this loop, we pick the minimum index of the two characters.
        Then we loop through the charIdx and replace all max index with min index.
        This way, we know what the minimum index of each character will be.
        */
        for(int i = 0; i < s1.length(); i++) {
            int pos1 = s1.charAt(i)-'a';
            int pos2 = s2.charAt(i)-'a';
            int minIdx = 0;
            int maxIdx = 0;
            if(charIdx[pos1] < charIdx[pos2]) {
                minIdx = charIdx[pos1];
                maxIdx = charIdx[pos2];
            }else {
                minIdx = charIdx[pos2];
                maxIdx = charIdx[pos1];
            }
            replaceIdx(charIdx, minIdx, maxIdx);
        }
        StringBuilder result = new StringBuilder();

        //When we build the result string now, all characters will have their minimum possible index.
        for(int i = 0; i < len; i++) {
            int pos = baseStr.charAt(i)-'a';
            result.append((char)('a'+charIdx[pos]));
        }

        return result.toString();
    }

    public void replaceIdx(int[] charIdx, int minIdx, int maxIdx) {
        for(int i = 0; i < 26; i++) {
            if(charIdx[i] == maxIdx) {
                charIdx[i] = minIdx;
            }
        }
    }
}
