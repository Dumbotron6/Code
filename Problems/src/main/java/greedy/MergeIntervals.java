package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    /*
    https://leetcode.com/problems/merge-intervals/
     */

    /*
    We sort the intervals because we cannot find overlapping intervals without it being in order.
    We sort it based on beginning (0 index). Now we know intervals overlap when the end of the previous interval
        is greater than or equal to beginning of current interval. That is what we check.
    To get the overlap end, we check which end is greater; the overlapping previous end or the current end and choose that
        as the end.
    The final if condition is present because we might reach the end of the array and get a new end, but we wouldn't have
        inserted it. Ex. in array [[1,3],[4,7],[5,6]].
     */
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> finalList = new ArrayList<int[]>();
        int[] prev = intervals[0];
        for(int i = 1; i < intervals.length; i++) {
            if(prev[1] >= intervals[i][0]) {
                prev[1] = Math.max(intervals[i][1], prev[1]);
            }else {
                finalList.add(prev);
                prev = intervals[i];
            }
        }
        if(finalList.isEmpty() || finalList.get(finalList.size()-1)[1] < prev[1])
            finalList.add(prev);

        int[][] finalArray = new int[finalList.size()][2];
        finalList.toArray(finalArray);

        return finalArray;
    }
}
