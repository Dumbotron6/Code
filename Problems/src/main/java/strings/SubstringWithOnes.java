package strings;

public class SubstringWithOnes {
    /*
    https://leetcode.com/problems/number-of-substrings-with-only-1s/
     */

    public int numSub(String s) {
        int cnt = 0;
        int total = 0;
        int mod = (int)1e9 + 7;

        for(char c : s.toCharArray()) {
            if(c == '1') {
                cnt++;
            }else {
                cnt = 0;
            }
            total = (total + cnt)%mod;
        }

        return total;
    }
}
