package greedy;

import java.util.Arrays;

public class MinOperationsMedianK {
    /*
    https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k/
     */

    /*
    For a median ie, middle element in the sorted array to be equal to k, nothing on the left should be > k
        and nothing on the right should be < k.
    So everything on left > k should be reduced to k. Similar operation for right.
    In a sorted array, only one of the two will happen in most cases even though we iterate through the whole array.
    So we can if condition before both for loop to check only half the array.
    */
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        long ops = 0;

        int median = len/2;

        for(int i = 0; i <= median; i++) {
            if(nums[i] > k) {
                ops += nums[i]-k;
            }
        }

        for(int i = median; i < len; i++) {
            if(nums[i] < k) {
                ops += k-nums[i];
            }
        }

        return ops;
    }
}
