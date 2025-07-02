package arrays;

import java.util.HashSet;
import java.util.Set;

public class MinimumOperationsToMakeArrayK {
    /*
    https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/
     */

    public int minOperations(int[] nums, int k) {
        /*
        We don't need the count of each distinct number.
        As long as there is atleast number greater, then that will take a step to reduce.
        */
        Set<Integer> distinct = new HashSet<Integer>();

        for(int num : nums) {
            if(num < k) {
                return -1;
            }
            distinct.add(num);
        }

        //If distinct doesn't have k, then it's lower than everything in distinct, so will require an additional step.
        if(distinct.contains(k)) {
            return distinct.size()-1;
        }

        return distinct.size();
    }
}
