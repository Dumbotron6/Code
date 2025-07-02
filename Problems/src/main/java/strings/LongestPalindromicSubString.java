package strings;

public class LongestPalindromicSubString {
    /*
    https://leetcode.com/problems/longest-palindromic-substring/description/
     */

    /*
    In a regular palindrome problem, we place pointers at beginning and end, then shrink them to check matching characters.
    Here, we pick a character and move the two pointers outwards (<-a->) checking if characters match.
    The reason for two while loops is because of odd and even length strings.
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int lReturn = 0, rReturn = 0;
        int max = 0;
        for(int i = 0; i < len; i++) {
            int left = i,
                    right = i;
            while(left >= 0 && right < len) {
                if(s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }else
                    break;
            }
            if(right-left+1 > max) {
                max = right-left+1;
                lReturn = left+1;
                rReturn = right-1;
            }

            left = i;
            right = i+1;
            while(left >= 0 && right < len) {
                if(s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }else
                    break;
            }
            if(right-left+1 > max) {
                max = right-left+1;
                lReturn = left+1;
                rReturn = right-1;
            }
        }

        return s.substring(lReturn, rReturn+1);
    }
}
