package arrays;

import java.util.Arrays;

public class CountFairPairs {
    /*
    https://leetcode.com/problems/count-the-number-of-fair-pairs/
     */

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        /*
        We can't find both together at once. So we find less than lower and less than upper+1.
        ie, if lower = 3 and upper = 6, then the difference between < 3 and < 7 will give >=3 and <=6.
        */
        long lowerPair = findPairsLessThan(nums, lower);
        long upperPair = findPairsLessThan(nums, upper+1);
        return upperPair-lowerPair;
    }

    public long findPairsLessThan(int[] nums, int bound) {
        long pairs = 0;
        int left = 0;
        int right = nums.length-1;

        /*
        If bound is 4, [0,1,2,3] At left = 0 and right = 3, anything added to 0 (1,2,3) will be less than 4. So that's 3 combos.
        At left = 1 and right = 3, there are two combos. That is why pairs += right-left.
        At left = 2 and right = 3, there are no combos.
        */
        while(left < right) {
            if(nums[left] + nums[right] < bound) {
                pairs += right-left;
                left++;
            }else {
                right--;
            }
        }

        return pairs;
    }
}
