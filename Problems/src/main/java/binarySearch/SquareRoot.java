package binarySearch;

public class SquareRoot {
    /*
    https://www.geeksforgeeks.org/problems/square-root/0?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=square-root
     */

    long floorSqrt(long x)
    {
        if(x == 1)
            return 1;

        /*
        Right is x/2 as a number can not be squared by more than it's half.
        The outlier is 1 which we are handling above.
        So the range to do binary search is between 1 and x/2.
         */
        long right = x/2;
        long left = 1;

        /*
        Instead of val*val, it can be val == x/2 as val*val can lead to overflow.
         */
        while(left <= right) {
            long val = left + (right-left)/2;
            if(val*val == x)
                return val;
            else if(val*val > x)
                right = val-1;
            else
                left = val+1;

        }

        return right;
    }
}
