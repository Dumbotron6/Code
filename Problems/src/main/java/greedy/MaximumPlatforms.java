package greedy;

import java.util.Arrays;

public class MaximumPlatforms {
    /*
    https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
     */

    /*
    We can for an array with arrival and departure time, sort that array and then look for overlaps (similar to NonOverlappingIntervals).
    Better solution is as below.
    When we sort the arrival and departure, we get the earliest arrival and departure times.
    i and j lets us track previous arrival and departure times.
    When current arrival is less than previous departure, then we need one more terminal.
    Once current arrival is greater than previous departure, then that means a terminal is freed up.
    Consider arrays [0900, 0930, 1000] and [0950, 0945, 1030].
    Sorted would be [0900, 0930, 1000] and [0945, 0950, 1030].
    Now when first train arrives(0900), we assign a terminal and check the next arrival(i++).
    When that arrival(0930) is less than the earliest departure(j)(0945), then we need another terminal.
        At this time, the max terminals is 2.
    On next arrival(1000), the previous departure(0945) has happened, so a terminal has freed up and the occupancy drops to 1.
     */
    static int findPlatform(int arr[], int dep[], int n)
    {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0;
        int j = 0;
        int terminals = 0;
        int maxTerminals = 0;

        while(i < arr.length && j < dep.length) {
            if(arr[i] <= dep[j]) {
                terminals++;
                i++;
            }else {
                terminals--;
                j++;
            }

            maxTerminals = Math.max(maxTerminals, terminals);
        }

        return maxTerminals;
    }
}
