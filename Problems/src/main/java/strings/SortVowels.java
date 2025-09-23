package strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SortVowels {
    /*
    https://leetcode.com/problems/sort-vowels-in-a-string/
     */

    /*
    Get all vowels in an array, sort them, add them into final array before returning that as string.
    */
    public String sortVowels(String s) {
        int len = s.length();
        char[] resultArr = new char[len];

        Set<Character> vowels = new HashSet<Character>(Arrays.asList(new Character[]{'A','E','I','O','U','a','e','i','o','u'}));

        StringBuilder vowString = new StringBuilder();

        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(!vowels.contains(c)) {
                resultArr[i] = c;
            }else {
                resultArr[i] = '.';
                vowString.append(c);
            }
        }

        char[] vowCharArray = vowString.toString().toCharArray();
        Arrays.sort(vowCharArray);

        int i = 0;
        int j = 0;
        while(j < vowCharArray.length) {
            while(resultArr[i] != '.') {
                i++;
            }
            resultArr[i++] = vowCharArray[j++];
        }

        return new String(resultArr);
    }
}
