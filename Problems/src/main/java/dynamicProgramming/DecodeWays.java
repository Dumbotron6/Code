package dynamicProgramming;

public class DecodeWays {
    /*
    https://leetcode.com/problems/decode-ways/
     */

    //This solution works and the approach is the same but a more concise and efficient way is written below.
    public int numDecodings(String s) {
        int n = s.length();
        Integer[] decodes = new Integer[n];
        if(s.charAt(0) == '0') {
            return 0;
        }
        decodes[0] = recursionHelper(s, n, 0, decodes);

        return decodes[0];
    }

    public int recursionHelper(String s, int n, int i, Integer[] decodes) {
        if(i == n) {
            return 1;
        }
        /*
        Why return 1? Because n can only be reached when coming from i+2.
        Which means similar to i == n-1 condition, s.charAt(i) > '0', at n-2, we got a valid double digit.
        */
        if(decodes[i] != null) {
            return decodes[i];
        }

        if(i == n-1) { //If last char in string, no need to check for double digit.
            if(s.charAt(i) > '0') {
                decodes[i] = 1;
            }else {
                decodes[i] = 0;
            }
            return decodes[i];
        }

        if(s.charAt(i) == '0') { //If 0, nothing can begin from here.
            decodes[i] = 0;
            return decodes[i];
        }

        int val = Integer.parseInt(s.substring(i, i+2)); //Checks if i, i+1 forms an alphabet.
        int rec1 = recursionHelper(s, n, i+1, decodes); //Check the combos from i+1.
        int rec2 = 0;
        decodes[i] = 0;
        /*
        Why declare 0? Incase i+1 and i+2 return 0, which means nothing can begin from i+1 and i+2
            which in turn means nothing can begin from i.
        */

        if(val < 27) { //If i, i+1 forms an alphabet, then check the combos from i+2.
            rec2 = recursionHelper(s, n, i+2, decodes);
        }

        decodes[i] += rec1 + rec2;

        return decodes[i];
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
