package dynamicProgramming;

public class HouseRobber {
    /*
    https://leetcode.com/problems/house-robber/description/
     */

    /*
    Below solution also has a DP solution with memo but a better approach than the previously submitted one.
    dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]); What does this mean?
    dp[i-2]+nums[i] means we are robbing the current house, so we take current house plus the i-2 house.
    dp[i-1] means we are not robbing the current house and instead skipping it because the i-1 (the previous) house has greated value.
    But we still store the value in dp[i]. Why? Because at any current house, we keep track of the max sum of the houses robbed so far.
    Take an example of [2,1,3,4]. At the end, dp[] would be [2,2,5,6]. Or if the array was [2,1,4,3], dp[] would be [2,2,6,6].
    So if the array was [2,1,3,4,7,6] or [2,1,4,3,7,6], the final value would be 13 in both cases because we kept track of max sum before 7 and 6.
    This lets us even find the max for [2,1,0,3,4,7,6] as both 1 and 0 are skipped because 2 was the max till 3.
    */
    public int robOptimal(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }

        return dp[len-1];
    }

    /*
    tracker is used to keep track of all houses visited as starting point.
    The first for loop is to start the robbery at each house from 0. Like check if started at 0 or 1 or 2 etc.
    The second for loop is to rob the +2 house and the loop to check if +3 or +4 etc houses provide additional value.
    */
    public int rob(int[] nums) {
        int maxVal = 0;
        Integer[] tracker = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            maxVal = Math.max(maxVal, robHouse(nums, i, tracker));
        }
        return maxVal;
    }

    public int robHouse(int[] nums, int ptr, Integer[] tracker) {
        if(ptr >= nums.length) {
            return 0;
        }
        if(tracker[ptr] != null) {
            return tracker[ptr];
        }
        int maxVal = 0;
        for(int i = 2; ptr+i < nums.length; i++) {
            maxVal = Math.max(maxVal, robHouse(nums, ptr+i, tracker));
        }

        tracker[ptr] = maxVal+nums[ptr];
        return tracker[ptr];
    }
}
