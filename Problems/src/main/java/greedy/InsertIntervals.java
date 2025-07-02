package greedy;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervals {
    /*
    https://leetcode.com/problems/insert-interval/description/
     */

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int start = -1;
        int end = -1;

        List<int[]> finalList = new ArrayList<int[]>();

        int i = 0;
        //We skip over all intervals which have end less than the newInterval that we are supposed to insert.
        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            finalList.add(intervals[i]);
            i++;
        }

        /*
        If intervals has ended, then set newInterval as the array to be inserted.
        If not, find the range. The beginning point(start) will always be the least of remaining intervals or newInterval.
        To start off, we set the end to least of remaining intervals or newIntervals.
        The reason being, we know that the range in newInterval will be inserted whenever the end becomes
            less than one of the beginnings in the remaining intervals.
         */
        if(i < intervals.length) {
            start = Math.min(intervals[i][0], newInterval[0]);
            end = Math.min(intervals[i][1], newInterval[1]);
        }else {
            start = newInterval[0];
            end = newInterval[1];
        }

        while(i < intervals.length && end >= intervals[i][0]) {
            end = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        finalList.add(new int[]{start, end});

        while(i < intervals.length) {
            finalList.add(intervals[i]);
            i++;
        }

        int[][] returnArray = new int[finalList.size()][];
        finalList.toArray(returnArray);

        return returnArray;
    }
}
