package strings;

public class PalindromicSubstrings {
    /*
    https://leetcode.com/problems/palindromic-substrings/description/
     */

    //Same logic as LongestPalindromicSubstring. Instead of the longest substring, here we return the total count.
    public int countSubstrings(String s) {
        int pali = 0;
        int len = s.length();

        for(int i = 0; i < len; i++) {
            int left = i, right = i;
            while(left >= 0 && right < len) {
                if(s.charAt(left) != s.charAt(right)) {
                    break;
                }
                pali++;
                left--;
                right++;
            }

            left = i; right = i+1;
            while(left >= 0 && right < len) {
                if(s.charAt(left) != s.charAt(right)) {
                    break;
                }
                pali++;
                left--;
                right++;
            }
        }

        return pali;
    }
}
