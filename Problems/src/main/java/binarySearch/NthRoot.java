package binarySearch;

public class NthRoot {
    /*
    https://www.geeksforgeeks.org/problems/find-nth-root-of-m5843/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-nth-root-of-m
     */

    public int NthRoot(int n, int m)
    {
        int left = 1;
        int right = m;

        while(left <= right) {
            int mid = left + (right-left)/2;
            int val = (int)Math.pow(mid, n);

            if(val == m)
                return mid;
            else if(val > m)
                right = mid-1;
            else
                left = mid+1;

        }

        return -1;
    }
}
