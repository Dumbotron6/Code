package strings;

public class StringToIntegerAtoi {
    /*
    https://leetcode.com/problems/string-to-integer-atoi/description/

    "42" and " 42" should return 42.
    "127jshe09" should return 127.
    "-892" should return -892.
    If above INT_MAX(2147483647) or below INT_MIN(-2147483648) value, return INT_MAX or INT_MIN.
     */

    public int myAtoi(String s) {
        int returnValue = 0;
        int sign = 0;
        int i = 0;

        //Skip whitespaces and find positive or negative. i gives us position to start parsing numbers.
        while(i < s.length()) {
            if(s.charAt(i) == ' ')
                i++;
            else if(s.charAt(i) == '+' || s.charAt(i) == '-') {
                sign = s.charAt(i++) == '+' ? 1 : -1;
                break;
            } else {
                sign = 1;
                break;
            }
        }

        //If end of string is reached, then return 0.
        if(i == s.length())
            return returnValue;

        while(i < s.length()) {
            //If something other than number, end loop.
            if(s.charAt(i) < '0' || s.charAt(i) > '9')
                break;
            /*
            Below part is a bit tricky.
            returnValue is int. So we need to make sure we don't exceed that value during calculation.
            returnValue > Integer.MAX_VALUE / 10 means value is above 214748364.
            So whatever number we get next would make the total value above INT_MAX.
            The part after || is for INT_MIN.
            If returnValue is 214748364 and the next number is above 7, when the number is
                negative(when we finally factor in the sign), it would be at or exceeding INT_MIN.
            If it's positive even then, it'll be above(if above 7) or at(if 7) INT_MAX value.
             */
            if(returnValue > Integer.MAX_VALUE / 10 || (returnValue == Integer.MAX_VALUE / 10 && s.charAt(i) > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            returnValue = returnValue*10 + (s.charAt(i++) - '0');
        }

        return sign * returnValue;
    }
}
