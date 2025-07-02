package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParantheses {
    /*
    https://leetcode.com/problems/generate-parentheses/
     */

    public List<String> generateParenthesis(int n) {
        List<String> retResult = new ArrayList<String>();

        backTracker(n, new StringBuilder(), 0, 0, retResult);
        return retResult;
    }

    public void backTracker(int n, StringBuilder paran, int open, int close, List<String> retResult){
        if(close == n){ //When close reaches n, it means we have used up all pairs.
            retResult.add(paran.toString());
        }else {
            if(open < n){
                paran.append('(');
                backTracker(n, paran, open+1, close, retResult);
                paran.deleteCharAt(paran.length()-1); //The reason for this is backtracking.
                /*
                The reason for this popping is, when we can end up with one element lesser.
                For example, if n=3, then the first result would be ((())).
                When we do backtracking, we end up such that we'd be left with (( [When the last ( in ((( is popped].
                Now ) can happen in the below code and we'd end up with (().
                 */
            }
            if(close < open){
                paran.append(')');
                backTracker(n, paran, open, close+1, retResult);
                paran.deleteCharAt(paran.length()-1);
            }
        }
    }

}
