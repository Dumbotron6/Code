package binarySearch;

public class PeakIndex {
    /*
    https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
     */

    /*
    We use binary search. Both left and right should be smaller than mid.
    Multiple indexes may satisfy so we kepe searching using binary search. For example [1,2,3,4,5,1].
    We keep moving towards the greater element every time in the binary search, so that we get closer to the peak each time.
    */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length-1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            if(arr[mid] > arr[mid+1]) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return right+1;
    }
}
