package arrays;

import java.util.Arrays;

public class ThreeSumClosest {
    /*
    https://leetcode.com/problems/3sum-closest/
     */

    /*
    In this solution, we sort the array so duplicates are easier to trace and we can move left or right
        instead of iterating and finding the sum over the whole array.
     */
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[nums.length-1];

        for(int i = 0; i < nums.length - 2; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                int left = i+1;
                int right = nums.length-1;

                while(left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    //Check if the current sum is closer than previous sum.
                    if(Math.abs(target - sum) < Math.abs(target - closest)) {
                        closest =  sum;
                    }

                    /*
                    The purpose of this is to bring the sum close to the target with each iteration.
                    So when target is greater than the sum, we bring it closer by providing a higher left number.
                    Same is the case if the target is lesser. We bring the target closer by providing a smaller
                        right number.
                    The while loops are for skipping duplicates of numbers that were just checked.
                     */
                    if(sum < target) {
                        left++;
                        while(left < right && nums[left] == nums[left-1])
                            left++;
                    }else {
                        right--;
                        while(left < right && nums[right] == nums[right+1])
                            right--;
                    }
                }
            }
        }

        return closest;
    }
}
