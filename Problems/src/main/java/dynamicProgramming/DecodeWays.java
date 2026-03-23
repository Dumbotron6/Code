package dynamicProgramming;

public class DecodeWays {
    /*
    https://leetcode.com/problems/decode-ways/
     */

    //This solution works and the approach is the same but a more concise and efficient way is written below.
    public int numDecodings(String s) {
        int n = s.length();
        Integer[] dp = new Integer[n];

        dp[0] = recursionHelper(s, n, dp, 0);

        return dp[0];
    }

    //This is used to check number of combinations if i is taken as a starting point.
    public int recursionHelper(String s, int n, Integer[] dp, int i) {
        if(i == n) {
            return 1;
        }
        //returning 1 as, if n is reached, then that means it was reached via valid means, either from n-1 or n-2.

        if(dp[i] != null) {
            return dp[i];
        }

        if(s.charAt(i) == '0') { //No combinations can happen if 0 is taken as a starting point.
            dp[i] = 0;
            return dp[i];
        }

        dp[i] = recursionHelper(s, n, dp, i+1); //i is starting point, single digit.

        if(i+1 < n) { //If we can take two digits ie, i != n-1.
            if(s.charAt(i) == '2' && s.charAt(i+1) <= '6') { //Double digit should be less than or equal to 26.
                dp[i] += recursionHelper(s, n, dp, i+2); //i is starting point, double digit.
            }else if(s.charAt(i) == '1') {
                dp[i] += recursionHelper(s, n, dp, i+2); //i is starting point, double digit.
            }
        }

        return dp[i];
    }

    public int numDecodingsOptimal(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        /*
        Building on previously submitted recursive solution, if s.charAt(i) == '0', no combos can begin from i.
        We assign dp[n] = 1 as dp[n] can only be reached if s.substring(n-2, n) forms a valid double digit.
        So we always check combos from i+1 (dp[i+1]) and if we get a valid double digit,
            we check for combos from i+2 (dp[i+2]).
        */
        for(int i = n-1; i >= 0 ; i--) {
            if(s.charAt(i) == '0') {
                dp[i] = 0;
            }else {
                dp[i] = dp[i+1];
                if(i < n-1 && Integer.parseInt(s.substring(i, i+2)) < 27) {
                    dp[i] += dp[i+2];
                }
            }
        }

        return dp[0];
    }
}
