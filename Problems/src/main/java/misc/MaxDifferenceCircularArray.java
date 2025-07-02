package misc;

public class MaxDifferenceCircularArray {
    /*
    https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
     */

    public int maxAdjacentDistance(int[] nums) {
        int max = 0;

        for(int i = 1; i < nums.length; i++) {
            max = Math.max(max, Math.abs(nums[i]-nums[i-1]));
        }
        max = Math.max(max, Math.abs(nums[0]-nums[nums.length-1]));

        return max;
    }
}
