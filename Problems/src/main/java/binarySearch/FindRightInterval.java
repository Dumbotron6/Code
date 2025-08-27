package binarySearch;

import java.util.Arrays;

public class FindRightInterval {
    /*
    https://leetcode.com/problems/find-right-interval/
     */

    /*
    What we need to find is the end of each interval (end[i]) <= start of another interval (start[j]).
    Here, we need to find the minimum value of start.
    So, we sort by start, then for each end, we use binary search to find the minimum start.
    */
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[][] sortIntervals = new int[len][2];
        int[] result = new int[len];

        for(int i = 0; i < len; i++) {
            sortIntervals[i][0] = intervals[i][0]; //Start value.
            sortIntervals[i][1] = i; //Respective index.
        }

        //Sort by start.
        Arrays.sort(sortIntervals, (a, b) -> a[0]-b[0]);

        //For each end, use binary sort to find minimum i that is greater.
        for(int i = 0; i < len; i++) {
            int left = 0, right = len-1;

            while(left < right) {
                int mid = left+(right-left)/2;
                if(sortIntervals[mid][0] >= intervals[i][1]) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }

            if(sortIntervals[left][0] >= intervals[i][1]) {
                result[i] = sortIntervals[left][1];
            }else {
                result[i] = -1;
            }
        }

        return result;
    }
}
