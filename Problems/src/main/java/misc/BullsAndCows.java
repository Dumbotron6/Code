package misc;

import java.util.HashSet;
import java.util.Set;

public class BullsAndCows {
    /*
    https://leetcode.com/problems/bulls-and-cows/
     */

    public String getHint(String secret, String guess) {
        Set<Integer> bulls = new HashSet<Integer>(); //Store positions where digits match.
        int cows = 0;
        int[] digitCount = new int[10]; //Count non matching digits.
        int len = secret.length();

        for(int i = 0; i < len; i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls.add(i);
            }else { //Add to non matching.
                digitCount[secret.charAt(i)-'0']++;
            }
        }

        for(int i = 0; i < len; i++) {
            //Check if non matching digit exists elsewhere.
            if(!bulls.contains(i) && digitCount[guess.charAt(i)-'0'] > 0) {
                digitCount[guess.charAt(i)-'0']--;
                cows++;
            }
        }

        String result = ""+bulls.size()+"A"+cows+"B";
        return result;
    }

    public String getHintBetter(String secret, String guess) {
        int cows = 0;
        int bulls = 0;
        int[] secretCount = new int[10]; //Count non matching digits of secret.
        int[] guessCount = new int[10]; //Count non matching digits of guess.
        int len = secret.length();

        for(int i = 0; i < len; i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }else { //Add to non matching.
                secretCount[secret.charAt(i)-'0']++;
                guessCount[guess.charAt(i)-'0']++;
            }
        }

        //The digits ex. 0 or 4 will have counts in both arrays if positions mismatch.
        for(int i = 0; i < 10; i++) {
            cows += Math.min(secretCount[i], guessCount[i]);
        }

        String result = ""+bulls+"A"+cows+"B";
        return result;
    }
}
