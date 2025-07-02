package misc;

public class IsSubsequence {
    /*
    https://leetcode.com/problems/is-subsequence/
     */

    public boolean isSubsequence(String s, String t) {
        int sCount = 0;

        for(int i = 0; i < t.length() && sCount < s.length(); i++) {
            if(t.charAt(i) == s.charAt(sCount)) {
                sCount++;
            }
        }

        return sCount == s.length();
    }
}
