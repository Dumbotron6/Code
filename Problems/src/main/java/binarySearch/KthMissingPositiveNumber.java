package binarySearch;

public class KthMissingPositiveNumber {

    /*
    https://leetcode.com/problems/kth-missing-positive-number/description/
     */

    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        /*
        Need to find where the number in the position is greater than k.
        For example, in [2,3,4,5,7,10], the ideal numbers are [1,2,3,4,5,6].
        In i==4, 7-5 is 2. So the position is off by 2.
        In i==5, 10-6 is 4. So the position is off by 4.
        If k is 3, then the missing number lies between 7 and 10 which is 8.
         */
        while(left <= right){
            int mid = left + (right-left)/2;
            int diff = arr[mid] - (mid+1);
            if(diff >= k)
                right = mid-1;
            else
                left = mid+1;
        }
        /*
        Need to return arr[right] + k - missing
        Missing is arr[right] - right - 1;
        So it can be right+k+1 or low+k.
         */
        return right + k + 1;
    }

}
