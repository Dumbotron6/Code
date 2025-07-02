package binarySearch;

import java.util.Arrays;

public class ShipPackagesWithinNDays {
    /*
    https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
     */

    public int shipWithinDays(int[] weights, int days) {

        /*
        We find the range first.
        If shipped within 1 day, then the min capacity should be the sum of weights.
        If shipped within weights.length days, then the min capacity should be the max of weights.
         */
        int right = Arrays.stream(weights).sum();
        int left = Arrays.stream(weights).max().orElse(0);

        /*
        NOTE!!!
        If left <= right, then right = mid-1, return left.
        This is because, if we do right = mid-1, then mid will be the answer.
        So when left==right, left will move to mid+1 which was the previous mid ie. the answer.
        We keep searching as we want the minimum weight capacity.
         */
        while(left < right) {
            int mid = left + (right-left)/2;
            if(fulfillsCapacity(weights, days, mid)) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return right;
    }

    public boolean fulfillsCapacity(int[] weights, int days, int mid) {
        int dayCount = 1;
        int sum = 0;
        for(int i = 0; i < weights.length; i++) {
            /*
            When sum becomes greater, we set the new weight to current.
            We don't have to worry about loop exiting when sum has a value because dayCount starts at 1.
            dayCount can be set at 1 as we know for sure at min there will be 1 day.
             */
            if(sum + weights[i] > mid) {
                sum = weights[i];
                dayCount++;
            }else {
                sum+= weights[i];
            }
        }
        return dayCount <= days;
    }

    //Alternate for when dayCount starts at 0.
    public boolean fulfillsCapacityAly(int[] weights, int days, int mid) {
        int dayCount = 0;
        int sum = 0;
        for(int i = 0; i < weights.length; i++) {
            if(sum + weights[i] > mid) {
                i--;
                sum = 0;
                dayCount++;
            }else {
                sum+= weights[i];
            }
        }
        if(sum > 0)
            dayCount++;
        return dayCount <= days;
    }
}
