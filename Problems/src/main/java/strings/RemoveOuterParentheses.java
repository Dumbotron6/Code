package strings;

public class RemoveOuterParentheses {
    /*
    https://leetcode.com/problems/remove-outermost-parentheses/description/
     */
    public String removeOuterParentheses(String s) {
        /*
        For this, we need to keep everything except for the first '(' and last ')'.
        When the count is 0, then we have no '('. So no need to add that in the output.
        When the count is 1, then we have exactly one '(' left (that we already excluded from output).
        So we shouldn't add the ')' that we have.
         */
        String returnString = "";

        int count = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                if(count > 0)
                    returnString += '(';
                count++;
            }else {
                if(count > 1)
                    returnString += ')';
                count--;
            }
        }

        return returnString;
    }
}
