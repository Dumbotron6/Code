package strings;

public class CountHomogenSubstring {
    /*
    https://leetcode.com/problems/count-number-of-homogenous-substrings/description/
     */

    public int countHomogenous(String s) {
        char prev = s.charAt(0);
        long total = 0;
        int cnt = 0;

        int mod = (int)1e9+7;

        for(char c : s.toCharArray()) {
            if(prev == c) {
                cnt++;
            }else {
                total = (total + (long)cnt*(cnt+1)/2)%mod;
                prev = c;
                cnt = 1;
            }
        }
        total = (total + (long)cnt*(cnt+1)/2)%mod;

        return (int)total;
    }
}
