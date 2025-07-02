package misc;

import java.util.Arrays;

public class SubsequenceSatisfyGivenSum {
    /*
    https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
     */

    /*
    We sort the array. Order doesn't matter when we want number of subsequence. Why? Take example [2,6,4,3].
    If we want subsequence [2,3], the order of the array doesn't matter. We will always skip 3 and 4.
    */
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length-1;

        int total = 0;
        long mod = (long) 1e9 + 7;

        /*
        Why do we precompute and store values instead of directly using Math.pow(2,k)?
        Because for large k, Math.pow(2,k) will exceed double value, leading to incorrect answer.
        So we precompute and store the modulo value beforehand.
        */
        long[] twoPow = new long[nums.length];
        twoPow[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            twoPow[i] = (twoPow[i-1]*2)%mod;
        }

        /*
        For every minimum number, we find the maximum number. Everything in between can form a subsequence with the minimum number included.
        How does that become 2^i? That's the formula. For every 2 numbers, to get the count of subsequence in between, 2^i works.
        [2,3,4], between 2 and 4, the subsequence is 4, between 3 and 4, the subsequence is 2.
        For each number in the subsequence, there are 2 choices, to take or not to take. Hence 2 power.
        */
        while(left <= right) {
            if(nums[left] + nums[right] <= target) {
                total = (int)(((long)total+twoPow[right-left])%mod);
                left++;
            }else {
                right--;
            }
        }

        return total;
    }
}
