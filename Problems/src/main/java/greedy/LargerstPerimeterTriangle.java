package greedy;

import java.util.Arrays;

public class LargerstPerimeterTriangle {
    /*
    https://leetcode.com/problems/largest-perimeter-triangle/
     */

    /*
    For largest perimeter, a+b > c sides is the rule.
    Safe to take 3 consecutive elements after sorting as if that isn't satisfied, going lower(i-3,i-4 etc)
        won't satisfy it either.
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for(int i = nums.length-1; i >= 2; i--) {
            if(nums[i] < nums[i-1]+nums[i-2]) {
                return nums[i]+nums[i-1]+nums[i-2];
            }
        }

        return 0;
    }
}
