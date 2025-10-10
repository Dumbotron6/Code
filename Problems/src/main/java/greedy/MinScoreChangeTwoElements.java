package greedy;

import java.util.Arrays;

public class MinScoreChangeTwoElements {
    /*
    https://leetcode.com/problems/minimum-score-by-changing-two-elements/description/
     */

    /*
    We can change first two indexes to the third index value, or change last two indexes to last-2 index value,
        or change first and last index value to first+1 and last-1 index value.
    By making two integers same, we will get min absolute difference(low) as 0.
    To find 'high', we have find difference between min and max element. Thus, we decide which two to change using above logic.
    We are doing is minimising the distance between the final min and max values.
    */
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length-1;
        int minSum = nums[len]-nums[2];

        if(nums[len-2]-nums[0] < minSum) {
            minSum = nums[len-2]-nums[0];
        }
        if(nums[len-1]-nums[1] < minSum) {
            minSum = nums[len-1]-nums[1];
        }

        return minSum;
    }
}
