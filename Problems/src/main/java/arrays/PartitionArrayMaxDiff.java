package arrays;

import java.util.Arrays;

public class PartitionArrayMaxDiff {
    /*
    https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/
     */

    /*
    We only care about the min and max values and their difference.
    So sort the array, find the max till which the difference is k. If not, increase count.
    */
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[0];
        int right = 0;
        int count = 0;

        while(right < nums.length) {
            if(nums[right]-min <= k ) {
                right++;
            }else {
                count++;
                min = nums[right];
            }
        }
        count++;

        return count;
    }
}
