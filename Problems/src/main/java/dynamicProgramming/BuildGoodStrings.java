package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class BuildGoodStrings {
    /*
    https://leetcode.com/problems/count-ways-to-build-good-strings/
     */

    public int countGoodStringsRecur(int low, int high, int zero, int one) {
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return countStrings(low, high, zero, one, 0, memo);
    }

    /*
    This approach is straight forward recursive. We calculate the good strings for a length, cache it's value,
        and if the length is hit again, we return that instead of calculating again.
    */
    public int countStrings(int low, int high, int zero, int one, int len, Map<Integer, Integer> memo) {
        if(len > high)
            return 0;
        if(memo.containsKey(len))
            return memo.get(len);
        int goodStr = 0;
        long mod = (long) 1e9 + 7;
        if(len >= low)
            goodStr++;

        goodStr += countStrings(low, high, zero, one, len+zero, memo);
        goodStr += countStrings(low, high, zero, one, len+one, memo);
        memo.put(len, (int)(goodStr%mod));
        return (int)(goodStr%mod);
    }

    /*
    This method is non-recursive but straight forward dp using memoization.
    At each i, we calculate ways it can be reached. Which can be reached by adding zero 0s or one 1s.
    That is what we do inside the loop. memo[i-zero] checks if i position can be reached from the previous(hypothetical) zero position
        before it. Same is the case for memo[i-one].
    If there had be a hypothetical zero or one position, it would've been marked in the memo array.
    This method is faster and it avoids map insertion which takes longer.
    */
    public int countGoodStringsDP(int low, int high, int zero, int one) {
        int[] memo = new int[high+1];
        memo[0] = 1;

        long mod = (long) 1e9+7;
        int goodStr = 0;
        for(int i = 1; i <= high; i++) {
            if(i >= zero && i >= one) {
                memo[i] = (int)((memo[i-zero] + memo[i-one])%mod);
            }else if(i >= zero) {
                memo[i] = (int)(memo[i-zero]%mod);
            }else if(i >= one) {
                memo[i] = (int)(memo[i-one]%mod);
            }
            if(i >= low) {
                goodStr = (int)((goodStr+memo[i]) % mod);
            }
        }
        return goodStr;
    }

}
