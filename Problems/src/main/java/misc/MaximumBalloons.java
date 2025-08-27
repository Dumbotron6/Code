package misc;

public class MaximumBalloons {
    /*
    https://leetcode.com/problems/maximum-number-of-balloons/
     */

    public int maxNumberOfBalloons(String text) {
        char[] freqCount = new char[26];

        for(char c : text.toCharArray()) {
            freqCount[c-'a']++;
        }

        int result = Integer.MAX_VALUE;

        for(char c : new char[]{'b','a','n'}) {
            result = Math.min(result, freqCount[c-'a']);
        }

        for(char c : new char[]{'l','o'}) {
            result = Math.min(result, freqCount[c-'a']/2);
        }

        return result;
    }
}
