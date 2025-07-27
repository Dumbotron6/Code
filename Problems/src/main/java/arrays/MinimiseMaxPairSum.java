package arrays;

import java.util.Arrays;

public class MinimiseMaxPairSum {
    /*
    https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
     */

    /*
    To minimise the sum of pairs, pair the smallest value with the largest value.
    */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int left = 0, right = nums.length-1;
        int max = 0;

        while(left < right) {
            max = Math.max(nums[left++]+nums[right--], max);
        }

        return max;
    }
}
