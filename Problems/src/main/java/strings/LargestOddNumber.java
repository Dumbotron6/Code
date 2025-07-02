package strings;

public class LargestOddNumber {
    /*
    https://leetcode.com/problems/largest-odd-number-in-string/description/

    If the string is "45678", the largest odd number is 4567. When "568", it is 5. When "42", it is "".
     */

    public String largestOddNumber(String num) {

        String large = "";

        /*
        We iterate backwards in the string.
        When we encounter an odd number, then that mean it is the last digit of the largest odd number in the string.
        So everything before that char and everything before it is the number we need.
         */

        for(int i = num.length() - 1; i >= 0; i--) {
            int temp = num.charAt(i) - '0';
            if(temp%2 == 1) {
                large = num.substring(0,i+1);
                break;
            }
        }

        return large;
    }
}
