package arrays;

public class MaxDifferenceIncreasing {
    /*
    https://leetcode.com/problems/maximum-difference-between-increasing-elements/
     */

    //Same as BuySellStock
    public int maximumDifference(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxDiff = -1;

        //At each index, check if it's minimum. If it's greater than minimum, calculate difference.
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= minVal) {
                minVal = nums[i];
            }else {
                maxDiff = Math.max(nums[i]-minVal, maxDiff);
            }
        }

        return maxDiff;
    }
}
