package strings;

public class CheckRotateString {
    /*
    https://leetcode.com/problems/rotate-string/description/

    deabc is a rotation of abcde
     */

    public boolean rotateString(String s, String goal) {
        /*
        For any string, say abcde, it we repeat it, it will have all possible rotation combinations
        abcdeabcde has eabcd, deabc, cdeab, bcdea
         */
        if(s.length() != goal.length())
            return false;

        String combined = s+s;

        for(int i = 0; i < s.length(); i++) {
            if(combined.charAt(i) == goal.charAt(0) && combined.substring(i, i+s.length()).equals(goal))
                return true;
        }
        return false;
    }
}
