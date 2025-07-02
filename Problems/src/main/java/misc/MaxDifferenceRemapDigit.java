package misc;

public class MaxDifferenceRemapDigit {
    /*
    https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
     */

    public int minMaxDifference(int num) {
        StringBuilder max = new StringBuilder(String.valueOf(num));
        StringBuilder min = new StringBuilder(String.valueOf(num));
        char maxReplace = '9';
        char minReplace = min.charAt(0);
        int len = max.length();

        for(int i = 0; i < len; i++) { //Identify first non 9.
            if(max.charAt(i) != '9') {
                maxReplace = max.charAt(i);
                break;
            }
        }

        for(int i = 0; i < len; i++) {
            if(max.charAt(i) == maxReplace) { //Replace first non 9 with 9 to get max.
                max.setCharAt(i, '9');
            }
        }

        for(int i = 0; i < len; i++) { //Replace first digit with 0 to get min.
            if(min.charAt(i) == minReplace) {
                min.setCharAt(i,'0');
            }
        }

        return Integer.parseInt(max.toString())-Integer.parseInt(min.toString());
    }
}
