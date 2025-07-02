package binarySearch;

import java.util.Arrays;

public class NumberOfDaysToMakeBouquet {
    /*
    https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
     */

    public int minDays(int[] bloomDay, int m, int k) {

        if(m*k > bloomDay.length)
            return -1;
        //The number of days can only lie between the min and max of blooming days.
        int left = Arrays.stream(bloomDay).min().orElse(0);
        int right = Arrays.stream(bloomDay).max().orElse(0);
        int minimum = -1;

        //We keep checking even if we find one number as we need the minimum number of days.
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(isBoquetEnough(bloomDay, m, k, mid)) {
                minimum = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return minimum;
    }

    public boolean isBoquetEnough(int[] bloomDay, int m, int k, int mid) {
        int flowers = 0;
        int bouquet = 0;

        for(int bloom : bloomDay) {
            if(bloom > mid) //if the days are greater than day being checked.
                flowers = 0;
            else {
                flowers++;
                /*
                When flowers reach number needed to make bouquet, reset count and increase bouquet size.
                 */
                if(flowers == k) {
                    flowers = 0;
                    bouquet++;
                }
                //If minimum number of bouquets reached.
                if(bouquet >= m)
                    return true;
            }
        }

        return false;
    }
}
