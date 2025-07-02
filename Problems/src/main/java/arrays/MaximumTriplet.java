package arrays;

public class MaximumTriplet {
    /*
    https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/
    https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/
    Both are the same. First one allows brute force while the second doesn't.
     */

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int max = 0;
        int maxDiff = 0;
        long maxProd = 0;

        for(int i = 0; i < n; i++) {
            maxProd = Math.max(maxProd, (long)maxDiff*nums[i]);
            maxDiff = Math.max(maxDiff, max-nums[i]); //Even if nums[i] was uses for maxProd, maxDiff will be used only in next i.
            max = Math.max(max, nums[i]);//Same with max.
        }
        /*
        We calculate maxDiff second as at n-1, if maxDiff is replaced it won't matter.
        maxProd is calculated first and with existing maxDiff.
        If maxProd is calculated last, at n-1 we'd reuse nums[i] or not use it for maxProd which is not right.
        */
        return maxProd;
    }
}
