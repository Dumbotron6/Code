package binarySearch;

import java.util.Arrays;

public class SmallestDivisor {
    /*
    https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
     */
    public int smallestDivisor(int[] nums, int threshold) {
        /*
        Max of array is the max range as dividing each element by it would give 1.
        Any number greater would yield the same result.
         */
        int right = Arrays.stream(nums).max().orElse(0);
        int left = 1;

        //We keep looking even if we find a divisor as we need the minimum divisor.
        while(left < right) {
            int mid = left + (right-left)/2;
            if(isDivisor(nums, threshold, mid))
                right = mid;
            else
                left = mid+1;
        }
        return right;
    }

    public boolean isDivisor(int[] nums, int threshold, int mid) {
        int sum = 0;

        for(int num : nums){
            sum += num/mid;
            if(num%mid > 0)
                sum++;
            //The sum needs to be less than the threshold.
            if(sum > threshold)
                return false;
        }
        return true;
    }
}
