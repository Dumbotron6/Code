package arrays;

public class ZeroFilledSubarrays {
    /*
    https://leetcode.com/problems/number-of-zero-filled-subarrays/
     */

    //We essentially need to identify all subarrays filled with 0, then calculate all sub arrays in that.
    public long zeroFilledSubarray(int[] nums) {
        long total = 0;
        long begin = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                if(begin == -1) {
                    begin = i;
                }
            }else {
                if(begin != -1) {
                    long n = i-begin;
                    total += (n*(n+1)/2);
                    begin = -1;
                }
            }
        }

        if(begin != -1) {
            long n = nums.length-begin;
            total += (n*(n+1)/2);
            begin = -1;
        }

        return total;
    }
}
