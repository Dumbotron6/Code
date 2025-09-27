package greedy;

import java.util.Arrays;

public class SmallestStringNumericValue {
    /*
    https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
     */

    public String getSmallestString(int n, int k) {
        char[] strArray = new char[n];
        Arrays.fill(strArray, 'a'); //Fill with 'a'.
        k -= n-1; //Remove count filled with 'a', cover the remaining with the loop below.
        int i = n-1;

        while(i >= 0) { //Fill from end, overwrite the 'a' when necessary from the back.
            if(k <= 25) { //Why 25? 26 would be z which is covered below.
                strArray[i] = (char)('a'+k-1);
                break;
            }else {
                strArray[i] = 'z';
            }
            k -= 25; //Wht 25? We already filled with 'a' so can't do -26.
            i--;
        }

        return new String(strArray);
    }
}
