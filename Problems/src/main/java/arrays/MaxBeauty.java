package arrays;

import java.util.Arrays;

public class MaxBeauty {
    /*
    https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/
     */

    /*
    We keep increasing the range whenever min(left) value and max(right) value is within 2k.
    Why? Max can reduce by k and min can increase by k, thereby totaling 2k.
        If we consider them say min is 1 and max is 5, k is 2, min+k is 3, max-k is 3. It is exactly 2k and meet in
            the middle.
    That is why we sort. Once that condition fails, we find a new starting point(by incrementing left).
     */
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 0;
        int max = 0;

        while(right < nums.length) {
            if(nums[right]-nums[left] <= 2*k) {
                right++;
            }else {
                left++;
            }
            max = Math.max(max, right-left);
        }

        return max;
    }
}
