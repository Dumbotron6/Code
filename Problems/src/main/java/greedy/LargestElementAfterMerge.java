package greedy;

public class LargestElementAfterMerge {
    /*
    https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations/description/
     */

    /*
    We apply the question in reverse, starting from the end. We don't actually need to delete nums[i].
    We simply need to keep track of the merged value, which here is val.
    Why solve in reverse? Consider [5,3,3]. If we go from front, we'd end up with 6 while the actual max will be 11.
    11 happens when we merge 3 and 3 first and then merge 5 and 6. If we go from the front, we will miss it.
    Going from the back ensures we will always have the maximum merged value at nums[i+1] (val) when comparing with nums[i].
    */
    public long maxArrayValue(int[] nums) {
        long max = nums[nums.length-1];
        long val = nums[nums.length-1];
        for(int i = nums.length-2; i >= 0; i--) {
            if(nums[i] <= val) {
                val += nums[i];
            }else {
                val = nums[i];
            }
            max = Math.max(max, val);
        }

        return max;
    }

    /*
    NOTE: We don't even need max and a Math.max().
    Because when if(nums[i] <= val) fails, nums[i] will be greater than max, so nums[i] will become the new max.
    ie, if nums[i] is greater than all merged so far, then nums[i] is obviously the greatest so far.
     */
}
