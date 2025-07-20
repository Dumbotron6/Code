package strings;

public class ValidPalindrome2 {
    /*
    https://leetcode.com/problems/valid-palindrome-ii/description/
     */

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;

        //If a mismatch is encountered, we have to skip the left character or right character, so we check both possibilities.
        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return validate(s, left+1, right) || validate(s, left, right-1);
            }
            left++;
            right--;
        }

        return true;
    }

    public boolean validate(String s, int left, int right) {
        while(left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
