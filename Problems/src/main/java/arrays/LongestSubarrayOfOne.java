package arrays;

public class LongestSubarrayOfOne {
    /*
    https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
     */

    //Sliding window, keep track of number of 0s.
    public int longestSubarray(int[] nums) {
        int zeroCnt = 0;
        int left = 0, right = 0;
        int maxOne = 0;

        while(right < nums.length) {
            if(nums[right] == 0) {
                zeroCnt++;
            }

            while(zeroCnt > 1) {
                if(nums[left++] == 0) {
                    zeroCnt--;
                }
            }

            maxOne = Math.max(maxOne, right-left);
            right++;
        }

        return maxOne;
    }
}
