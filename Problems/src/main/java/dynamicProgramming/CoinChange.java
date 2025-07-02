package dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    /*
   The understanding is, there are various ways to reach amount.
   Similarly, there are various ways to reach amount-k.
   For example, in [1,2,5] and 11, from 0, we can add 5,5,1 to reach 11 which is one way.
   Now from 10, we can add 1 to reach 1. But how many ways were there to reach 10? 5+5, 5+2+2+1, 2+2+2+2+2 etc.
   But we need the minimum of all of those. So say, we had to reach 11, the minimum would be 5+5+1.
   So when we have 5 total, we calculate the minimum ways to reach 11 from there and store it in the dp.
   The next time we reach total 5, we can directly take from this stored dp.
   There is a better but similar solution with array dp and reverse calculation below.
   There is an even more optimal solution below that. Bottom up approach.
   */
    public int coinChange(int[] coins, int amount) {
        Map<Long, Integer> dp = new HashMap<Long, Integer>();
        return recursionHelper(coins, amount, dp, 0);
    }

    public int recursionHelper(int[] coins, int amount, Map<Long, Integer> dp, long total) {
        //This means the coin added in the parent recursion call is invalid.
        if(total > amount) {
            return -1;
        }
        if(dp.containsKey(total)) {
            return dp.get(total);
        }
        /*
        If total is matched, then it means the coin added in the parent recursion call is valid.
        So we can do 1+recVal over there.
        */
        if(total == amount) {
            return 0;
        }

        /*
        recVal > -1 is to help determine if the coin added is invalid or not.
        If total exceeds amount, then it is invalid.
        */
        for(int i = coins.length-1; i >= 0; i--) {
            int recVal = recursionHelper(coins, amount, dp, total+coins[i]);
            if(recVal > -1) {
                if(!dp.containsKey(total)) {
                    dp.put(total, 1+recVal);
                }else {
                    dp.put(total, Math.min(dp.get(total), 1+recVal));
                }
            }
        }
        /*
        If total is not in the map at this point, then it means there is no way to reach to amount from here.
        */
        if(!dp.containsKey(total)) {
            dp.put(total, -1);
        }
        return dp.get(total);
    }

    /*
    There is an even more optimal solution below this. Bottom up approach.
     */
    public int coinChangeOptimal(int[] coins, int amount) {
        Integer[] dp = new Integer[amount+1];
        return recursionHelperOptimal(coins, dp, amount);
    }

    /*
    We use same concept as previously submitted solution, only in reverse.
    Instead of adding from 0 to amount, we subtract from amount to 0.
    Advantage is, we can int total instead of long and array dp as there will be no overflow.
    */
    public int recursionHelperOptimal(int[] coins, Integer[] dp, int total) {
        //This means the coin added in the parent recursion call is invalid.
        if(total < 0) {
            return -1;
        }

        /*
        If total becomes 0, then it means the coin removed from total in the parent recursion call is valid.
        So we can do 1+recVal over there.
        */
        if(total == 0) {
            return 0;
        }
        if(dp[total] != null) {
            return dp[total];
        }

        /*
        recVal > -1 is to help determine if the coin removed from total is invalid or not.
        If total goes below 0, then it is invalid.
        */
        for(int coin : coins) {
            int recVal = recursionHelperOptimal(coins, dp, total-coin);
            if(recVal > -1) {
                if(dp[total] == null) {
                    dp[total] = 1+recVal;
                }else {
                    dp[total] = Math.min(dp[total], 1+recVal);
                }
            }
        }
        /*
        If total is not in the map at this point, then it means there is no way to reach 0 from here.
        */
        if(dp[total] == null) {
            dp[total] = -1;
        }
        return dp[total];
    }

    /*
    Building on the previously submitted solution, we check if each coin can reach from a certain amount.
    Take [5,2,1] and amount 11 as example.
    Take the first loop when coin is 5. It will set dp[5] to 1 and dp[10] t0 1.
    Take the second loop when coin is 2, It will set dp[2] to 1, dp[3] to 2 etc till 6.
    It will dp[8] to 2 as dp[6] was already set to 1.
    Now take the third loop when coin is 1. It will set dp[1] to 1. dp[2] will not be set as coin 2 already set it to 1.
    To be clear, 5 will set it to [0,11,11,11,11, 1,11,11,11,11, 2,11].
                 2 will set it to [0,11, 1,11, 2, 1, 3,11, 4,11, 2,11].
                 1 will set it to [0, 1, 1, 2, 2, 1, 2, 3, 3, 5, 2, 3].
    Essentially, work from below and mark all the places from where 0 can be reached.
    */
    /*
    This is the bottom up approach. What we check is, from 0, what are all the ways we can build up to 11.
    We mark all the places the coins will land. Then we take the minimum if the same place is reached in
        the next coin. This way, we will get the minimum number of coins.
    */
    public int coinChangeBottomUp(int[] coins, int amount) {
        /*
        Why amount+1? Worst case scenario is when we have [1] coin, we will take amount number of coins.
        There is no way we will have amount+1 coins. So we fill by amount+1.
        */
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int coin : coins) {
            for(int i = 1; i <= amount; i++) {
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1+dp[i-coin]);
                }
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }

}
