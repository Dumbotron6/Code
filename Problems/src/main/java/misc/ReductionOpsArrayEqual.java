package misc;

import java.util.Arrays;

public class ReductionOpsArrayEqual {
    /*
    https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/description/
     */

    /*
    We sort the array, iterate backwards. When we find a smaller element, every element to the right have to be reduced to that element
        which is counted as one operation for each reduction.
    So we do ops += len-ptr.
    */
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length-1;
        int ptr = len-1;
        int ops = 0;

        while(ptr >= 0) {
            while(ptr >= 0 && nums[ptr] == nums[ptr+1]) {
                ptr--;
            }
            if(ptr >= 0) {
                ops += len-ptr;
            }
            ptr--;
        }

        return ops;
    }
}
