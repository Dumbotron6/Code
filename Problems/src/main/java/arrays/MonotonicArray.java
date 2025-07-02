package arrays;

public class MonotonicArray {
    /*
    https://leetcode.com/problems/monotonic-array/description/
     */

    public boolean isMonotonic(int[] nums) {
        boolean increase = false, decrease = false;

        for(int i = 1; i < nums.length; i++) {
            if(increase && nums[i] < nums[i-1]) {
                return false;
            }else if(decrease && nums[i] > nums[i-1]){
                return false;
            }
            if(nums[i] < nums[i-1]) {
                decrease = true;
            }else if(nums[i] > nums[i-1]) {
                increase = true;
            }
        }

        return true;
    }
}
