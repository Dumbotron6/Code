package binarySearch;

public class MinimumRotatedSortedArray {
    /*
    https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     */

    /*
    We use binary search and in the if condition, we use if(nums[mid] > nums[high]).
    This is because, if the [mid] value is higher than [high], then that means the array is rotated and the minimum value
        lies to the right of mid. If not, then that means we are in the portion where the array is in the regular
        sorted portion and minimum lies to the left.
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;

        while(low < high) {
            int mid = low + (high-low)/2;

            if(nums[mid] > nums[high]) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
