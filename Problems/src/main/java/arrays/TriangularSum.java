package arrays;

public class TriangularSum {
    /*
    https://leetcode.com/problems/find-triangular-sum-of-an-array/
     */

    public int triangularSum(int[] nums) {
        int len = nums.length-1;

        //Each digit will be added to its subsequent one. As that is done, the array will keep shrinking.
        while(len > 0) {
            int i = 0;
            while(i < len) {
                nums[i] += nums[i+1];
                nums[i] %= 10;
                i++;
            }
            len--;
        }

        return nums[0];
    }
}
