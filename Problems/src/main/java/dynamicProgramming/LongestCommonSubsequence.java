package dynamicProgramming;

public class LongestCommonSubsequence {
    /*
    https://leetcode.com/problems/longest-common-subsequence/description/
     */


    //This is not the optimal solution.
    public int longestCommonSubsequenceRecursion(String text1, String text2) {
        Integer[][] dp = new Integer[text1.length()][text2.length()];
        return findCommon(text1, text2, 0, 0, dp);
    }

    /*
    We use dynamic programming to remember the positions we already checked.
    What does dp[i][j] mean? Take "abcd" as text1 then dp[] would be [null,null,null,null].
    For "ab" text2, dp[0] would be [null, null, null, null]. Together, they would be [[null,null], [null,null], [null,null], [null,null]].
    Better explanation using diagram below.
    */
    public int findCommon(String text1, String text2, int i, int j, Integer[][] dp) {
        if(i >= dp.length || j >= dp[0].length) {
            return 0;
        }
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        if(text1.charAt(i) == text2.charAt(j)) {
            dp[i][j] = 1 + findCommon(text1, text2, i+1, j+1, dp);
        }else {
            dp[i][j] = Math.max(findCommon(text1, text2, i+1, j, dp), findCommon(text1, text2, i, j+1, dp));
        }
        return dp[i][j];
    }

    /*
    This is the optimal solution.
    This approach builds upon the below diagram and the previously submitted recursion logic.
    If text1.charAt(i-1) == text2.charAt(j-1), then character was found. So we do a +1.
    If not, then it came from left(j-1) or above(i-1). See diagram below.
    When they don't match, the previous value will be carried over till we reach a match.
    This takes n^2 time.
    The dp size has length+1 because we want to do a charAt(i-1). So we start at 0 minimum.
    */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /*              [i]
            a   b    c    d    e
      a  [null,null,null,null,null]
      c  [null,null,null,null,null]  [j]
      e  [null,null,null,null,null]

      When i and j are 0, text1.charAt(i) == text2.charAt(j). We found one char. Now we have to find from the second char onwards.
      Since one char was found, we move diagonally(Why? Cuz we can skip the first char in both, which was found),
            so i and j both become 1. Now b != c. So we can go either of two ways.
      We can make i as 2 and check c == c or we can make j as 2 and check b == 2. We have two possible options.
      ie, we check if second character of text1 equals third of text2, and if third of text1 equals second of text2.
      That's what we do in the above code. We do both and pick the max value among them.
      Why add 1? Because of the characters match, then we found a common char, so we increase count.
    */
}
