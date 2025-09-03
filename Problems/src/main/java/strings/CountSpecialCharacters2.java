package strings;

import java.util.Arrays;

public class CountSpecialCharacters2 {
    /*
    https://leetcode.com/problems/count-the-number-of-special-characters-ii/description/
     */

    public int numberOfSpecialChars(String word) {
        int[] maxIdxSmall = new int[26];
        int[] maxIdxLarge = new int[26];
        Arrays.fill(maxIdxSmall, -1);
        Arrays.fill(maxIdxLarge, -1);

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c >= 'a' && c <= 'z') { //Last occurance of lower case.
                maxIdxSmall[c-'a'] = i;
            }else if(maxIdxLarge[c-'A'] == -1) { //First occurance of upper case.
                maxIdxLarge[c-'A'] = i;
            }
        }

        int count = 0;

        for(int i = 0; i < 26; i++) {
            if(maxIdxSmall[i] != -1 && maxIdxLarge[i] != -1 && maxIdxSmall[i] < maxIdxLarge[i]) {
                count++;
            }
        }

        return count;
    }
}
