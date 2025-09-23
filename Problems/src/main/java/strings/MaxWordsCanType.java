package strings;

import java.util.HashSet;
import java.util.Set;

public class MaxWordsCanType {
    /*
    https://leetcode.com/problems/maximum-number-of-words-you-can-type/
     */

    public int canBeTypedWords(String text, String brokenLetters) {
        int len = text.length();
        Set<Character> broken = new HashSet<Character>();

        for(char c : brokenLetters.toCharArray()) {
            broken.add(c);
        }

        int i = 0;
        int total = 0;

        while(i < len) {
            if(broken.contains(text.charAt(i))) {
                while(i < len && text.charAt(i) != ' ') {
                    i++;
                }
            }else if(text.charAt(i) == ' ' || i == len-1){
                total++;
            }
            i++;
        }

        return total;
    }
}
