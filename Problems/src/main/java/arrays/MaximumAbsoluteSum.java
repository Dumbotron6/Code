package arrays;

public class MaximumAbsoluteSum {
    /*
    https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
     */

    public int maxAbsoluteSum(int[] nums) {
        /*
        Done using Maximum Subarray Sum (also a leetcode problem) – Kadane’s Algorithm.
        https://leetcode.com/problems/maximum-subarray/
        We calculate both the max sum and the min sum. The answer will be one of the two.
        */
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum < 0) {
                sum = 0;
            }
            maxSum = Math.max(sum, maxSum);
        }

        sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum > 0) {
                sum = 0;
            }
            minSum = Math.min(sum, minSum);
        }
        return Math.max(maxSum, Math.abs(minSum));
    }
}
