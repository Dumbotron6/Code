package arrays;

import java.util.HashSet;
import java.util.Set;

public class MaxUniqueSubarraySum {
    /*
    https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/
     */

    //Basically sum of all unique numbers, if no positive exists, return maximum negative number.
    public int maxSum(int[] nums) {
        int sum = 0;
        Set<Integer> uniques = new HashSet<Integer>();
        int neg = -101;

        for(int num : nums) {
            if(!uniques.contains(num) && num >= 0) {
                sum += num;
                uniques.add(num);
            }else if(num < 0 && num > neg) {
                neg = num;
            }
        }

        return !uniques.isEmpty() ? sum : neg;
    }
}
