package arrays;

public class DivideArrayMinCost {
    /*
    https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/
     */

    public int minimumCost(int[] nums) {
        int least = nums[0];
        int least2 = nums[1];
        int least3 = nums[2];

        for(int i = 3; i < nums.length; i++) {
            if(nums[i] < least3) {
                if(least3 < least2) {
                    least2 = least3;
                }
                least3 = nums[i];
            }else if(nums[i] < least2) {
                least2 = nums[i];
            }
        }

        return least+least2+least3;
    }
}
