package misc;

public class DigitsEqualAfterOps1 {
    /*
    https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
     */

    public boolean hasSameDigits(String s) {
        char[] vals = s.toCharArray();
        int len = vals.length;

        for(int l = len-2; l > 0; l--) {
            for(int i = 0; i <= l; i++) {
                int val = ((int)(vals[i] - '0') + (int)(vals[i+1] - '0'))%10;
                vals[i] = (char)(val+'0');
            }
        }

        return vals[0] == vals[1];
    }
}
