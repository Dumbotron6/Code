package arrays;

public class SmallestRange {
    /*
    https://leetcode.com/problems/smallest-range-i/description/
     */

    public int smallestRangeI(int[] nums, int k) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        /*
        We need to bring existing min and max as close as possible.
        No need to calculate every element in between as, if min and max are closer, then for other elements,
            it's possible to make them the same digit.
        Ex. For [1,3,4,5,6] and k = 3, everything can become 3 or 4.
        */
        if(min + k >= max - k) {
            return 0;
        }

        return (max-k)-(min+k);
    }
}
