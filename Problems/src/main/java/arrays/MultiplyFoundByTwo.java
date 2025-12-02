package arrays;

import java.util.Arrays;

public class MultiplyFoundByTwo {
    /*
    https://leetcode.com/problems/keep-multiplying-found-values-by-two/
     */

    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);

        for(int num : nums) {
            if(num == original) {
                original *= 2;
            }
        }

        return original;
    }
}
