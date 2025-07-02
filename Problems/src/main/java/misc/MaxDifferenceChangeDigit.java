package misc;

public class MaxDifferenceChangeDigit {
    /*
    https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/
     */

    //Builds on MaxDifferenceRemapDigit
    public int maxDiff(int num) {
        StringBuilder max = new StringBuilder(String.valueOf(num));
        StringBuilder min = new StringBuilder(String.valueOf(num));
        int len = max.length();
        char maxNonNine = '9';
        char minNonOne = min.charAt(0);

        for(int i = 0; i < len; i++) { //Identify first non nine digit.
            if(max.charAt(i) != '9') {
                maxNonNine = max.charAt(i);
                break;
            }
        }

        char diffDigit = min.charAt(0);
        for(int i = 0; i < len; i++) { //Identify first non one digit.
            if(min.charAt(i) != '1' && min.charAt(i) != '0') {
                diffDigit = min.charAt(i);
                break;
            }
        }

        replaceDigit(max, maxNonNine, '9', len);

        //Why do the below? It shouldn't have leading zeros.
        if(minNonOne == diffDigit) { //Means either num is single digit or first digit is non one.
            replaceDigit(min, minNonOne, '1', len);
        }else { //Replace first non one with 0.
            replaceDigit(min, diffDigit, '0', len);
        }

        return Integer.parseInt(max.toString())-Integer.parseInt(min.toString());
    }

    public void replaceDigit(StringBuilder str, char match, char replace, int len) {
        for(int i = 0; i < len; i++) {
            if(str.charAt(i) == match) {
                str.setCharAt(i, replace);
            }
        }
    }
}
