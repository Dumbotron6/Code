package greedy;

public class ValidParanthesis {
    /*
    https://leetcode.com/problems/valid-parenthesis-string/
     */

    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        /*
        We have two choices at every *. Treat the star as ( or ).
        minOpen treats it as ( while maxOpen treats it as ).
        If any point, maxOpen falls below 0, then ) has exceeded (. So we return false.
        Now if minOpen falls below 0, then it means we need not have treated some * as ). So we reset it to 0.
        If you're worried that setting it to 0 might cause use to miss some necessary * being treated as ),
            that is what maxOpen is for. As long as maxOpen >= 0, then we are good.
        So in the end, minOpen can be 0 and maxOpen can be 3, so we have leftOver * that were treated as (.
        But minOpen can't be greater than 0, then it means we have leftOver actual (.
        */
        for(char c : s.toCharArray()) {
            if(c == '(') {
                minOpen++;
                maxOpen++;
            }else if(c == ')') {
                minOpen--;
                maxOpen--;
            }else {
                minOpen--;
                maxOpen++;
            }
            if(maxOpen < 0) {
                return false;
            }
            minOpen = Math.max(minOpen, 0);
        }
        return minOpen == 0 || maxOpen == 0;
    }
}
