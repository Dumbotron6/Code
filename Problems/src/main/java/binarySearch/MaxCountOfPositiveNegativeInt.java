package binarySearch;

public class MaxCountOfPositiveNegativeInt {
    /*
    https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/
     */

    public int maximumCount(int[] nums) {
        int left = 0, right = nums.length-1;

        //Finds first occurance of positive num.
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(nums[mid] <= 0) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        int val = nums.length-left;
        left = 0; right = nums.length-1;
        //Finds first last occurance of negative num+1.
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(nums[mid] < 0) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return Math.max(val, left);
    }
}
