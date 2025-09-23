package strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VowelsGame {
    /*
    https://leetcode.com/problems/vowels-game-in-a-string/
     */

    /*
    As long as there are vowels, alice can win. Why?
    If odd number exists, alice can remove entire string.
    If even number exists, alice can remove 1 on the first turn, leaving odd number for bob
        who has to remove atleast 2, which would leave odd number of vowels,
        which alice can then remove.
    */
    public boolean doesAliceWin(String s) {
        Set<Character> vowels = new HashSet<Character>(Arrays.asList(new Character[]{'a','e','i','o','u'}));

        for(int i = 0; i < s.length(); i++) {
            if(vowels.contains(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
