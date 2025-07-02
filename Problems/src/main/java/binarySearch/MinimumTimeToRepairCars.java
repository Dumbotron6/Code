package binarySearch;

public class MinimumTimeToRepairCars {
    /*
    https://leetcode.com/problems/minimum-time-to-repair-cars/
     */

    public long repairCars(int[] ranks, int cars) {
        long left = 1;
        long right = ranks[0];
        //Can take any value as all mechanics will be working simultaneously. So the final result would be less than min mechanic's time.
        right = (long)cars*(long)cars*right;

        while(left <= right) {
            long mid = left + (right-left)/2;
            if(canRepair(ranks, cars, mid)) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return left;
    }

    /*
    Time = rank * count ^ 2 ---- so,
    count = (time/rank) ^ 1/2 ---- This is used to calculate the number of cars each mechanic can fix in time minutes.
    */
    public boolean canRepair(int[] ranks, int cars, long time) {
        long totalCars = 0;

        for(int rank : ranks) {
            totalCars += Math.sqrt(time/rank);
        }
        return totalCars >= cars;
    }
}
