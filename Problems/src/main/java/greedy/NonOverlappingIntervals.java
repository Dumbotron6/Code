package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    /*
    https://leetcode.com/problems/non-overlapping-intervals/submissions/1364594469/
     */

    /*
    We cannot compare overlaps without the intervals being ordered. So we sort.
    Since the intervals are sorted, we know that they overlap if the current small is less than the previous big.
    So removal count goes up.
    But also compare the current big and previous big and take the smallest. The reason being, we want the smallest
        range possible.
    Take the example of [[1,3],[1,7],[2,3],[3,4]]. If we take [1,7] as the prev, then it would mean we would be removing
        [2,3] and [3,4] on subsequent iterations. If we remove the largest range and keep [1,3] as the prev however,
         subsequent ranges won't be skipped.
    Note: Since we only ever use the big (1 index) number, we don't even need to store prev array. We can just track
        the previous big. ie, int prev = intervals[i][1];
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        int removal = 0;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int[] prev = intervals[0];

        for(int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if(current[0] < prev[1]) {
                removal++;
                if(current[1] < prev[1]) {
                    prev = current;
                }
            }else {
                prev = current;
            }
        }

        return removal;
    }
}
