package arrays;

import java.util.Arrays;

public class CutGridIntoSections {
    /*
    https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/
     */

    public boolean checkValidCuts(int n, int[][] rectangles) {
        int len = rectangles.length;
        int[][] xPoints = new int[len][2];
        int[][] yPoints = new int[len][2];

        /*
        We take all x axis start,end and store it in an array. Same for y-axis.
        But left bottom of the array is considered [0,0] as per description. So for n=5, the origin is [4,0] right?
        Not necessarily. This is just given to confuse. If we take [0,0] as the origin, it just means the
            matrix will be inverted when we visualise.
        */
        for(int i = 0; i < len; i++) {
            xPoints[i][0] = rectangles[i][0];
            xPoints[i][1] = rectangles[i][2];
            yPoints[i][0] = rectangles[i][1];
            yPoints[i][1] = rectangles[i][3];
        }

        /*
        Sort with all the start points. Why not the complexity of sorting by start and then end?
        For example, we xPoints we will get [0,3],[0,2]. We don't need to sort it to [0,2],[0,3].
        Why? Check explanation below.
        */
        Arrays.sort(xPoints, (a,b) -> a[0]-b[0]);
        Arrays.sort(yPoints, (a, b) -> a[0]-b[0]);

        //Either the x or y coordinates should have non overlap.
        return checkNonOverlap(xPoints) || checkNonOverlap(yPoints);
    }

    /*
    Store the max of all end points. If the start we encounter is <= old end,
        that means we have a non-overlapping section.
    Storing the max of end points lets us not having to sort by start and then end to identify overlaps.
    */
    public boolean checkNonOverlap(int[][] arr) {
        int count = 0;
        int prev_end = -1;
        for(int[] a : arr) {
            if(prev_end <= a[0]) {
                count++;
            }
            prev_end = Math.max(prev_end, a[1]);
        }

        return count >= 3;
    }
}
