package arrays;

import java.util.HashSet;
import java.util.Set;

public class MaximumErasureValue {
    /*
    https://leetcode.com/problems/maximum-erasure-value/
     */

    /*
    Use a set and two pointers to maintain unique subarray, check it's sum.
    */
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> uniques = new HashSet<Integer>();
        int left = 0, right = 0;
        int max = 0;
        int sum = 0;

        while(right < nums.length) {
            sum += nums[right];
            while(uniques.contains(nums[right])) {
                sum -= nums[left];
                uniques.remove(nums[left++]);
            }
            uniques.add(nums[right++]);
            max = Math.max(sum, max);
        }

        return max;
    }
}
