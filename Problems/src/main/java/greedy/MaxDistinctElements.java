package greedy;

import java.util.Arrays;

public class MaxDistinctElements {
    /*
    https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/
     */

    /*
    Check if the least element possible from each num is distinct.
    Why? Because since are sorting the numbers, the least will not be reachable (by adding -k) by subsequent greater numbers.
    To do this without maintaining a set, we use 'least' variable which stores the max of the least number we have far.
    By this logic, if the least is greater than whatever max we can possibly get, then there are no slots left to fill.
    */
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int least = Integer.MIN_VALUE;

        for(int num : nums) {
            if(least < num+k) {
                least = Math.max(least+1, num-k);
                ans++;
            }
        }

        return ans;
    }
}
