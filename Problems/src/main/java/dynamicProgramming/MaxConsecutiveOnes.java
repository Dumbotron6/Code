package dynamicProgramming;

public class MaxConsecutiveOnes {
    /*
    https://leetcode.com/problems/max-consecutive-ones-iii/description/
     */

    /*
    Keep a counter of the zeroes encountered. When it becomes more than k, we can start shorten the array from the left
        and maintain the zeroes at max k.
     */
    public int longestOnes(int[] nums, int k) {
        int zeroes = 0;
        int begin = 0;
        int end = 0;
        int max = 0;
        while(end < nums.length) {
            if(nums[end] == 0)
                zeroes++;
            while(zeroes > k) {
                if(nums[begin] == 0)
                    zeroes--;
                begin++;
            }
            max = Math.max(max, end-begin+1);
            end++;
        }
        return max;
    }
}
