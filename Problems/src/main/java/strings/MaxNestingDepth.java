package strings;

public class MaxNestingDepth {
    /*
    https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
     */

    public int maxDepth(String s) {
        int max = 0;
        int openCount = 0;

        for(char c: s.toCharArray()) {
            if(c == '(') {
                openCount++;
                max = Math.max(openCount, max);
            }else if(c == ')') {
                openCount--;
            }
        }

        return max;
    }
}
