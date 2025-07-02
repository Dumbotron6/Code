package dynamicProgramming;

public class HouseRobber2 {
    /*
    https://leetcode.com/problems/house-robber-ii/description/
     */

    public int rob(int[] nums) {
        if(nums.length < 2) {
            return nums[0];
        }
        if(nums.length < 3) {
            return Math.max(nums[0], nums[1]);
        }

        /*
        As the houses are circular, we cannot rob the first and last houses together.
        So we have to either skip the first house or the last house.
        So we calculate the max of both.
        */
        int maxRob = Math.max(getMaxRob(nums, 0, nums.length-1), getMaxRob(nums, 1, nums.length));

        return maxRob;
    }

    //Below code is same as House Robber 1. Only difference is, we use fixed variables instead of a dp[] array. We don't need it.
    public int getMaxRob(int[] nums, int begin, int end) {
        int rob1 = nums[begin]; //oldest
        int rob2 = Math.max(rob1, nums[begin+1]); //nearest

        for(int i = begin+2; i < end; i++) {
            int newRob = Math.max(rob1+nums[i], rob2);
            rob1 = rob2;
            rob2 = newRob;
        }

        return rob2;
    }
}
