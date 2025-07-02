package binarySearch;

public class SearchRotatedSortedArray {
    /*
    https://leetcode.com/problems/search-in-rotated-sorted-array/
     */

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if(nums[mid] == target)
                return mid;

            /*
            This if condition is to see if the portion we are checking left is rotated or not.
            Then that means the array is not rotated at all or that the rotation is in the right portion.
             */
            if(nums[low] <= nums[mid]) { //Check if left portion is not rotated, meaning, portion is regular ascending.
                if(target < nums[mid] && target >= nums[low]) //If target is to the left of mid(in the regular portion).
                    high = mid-1;
                else //If not, it means rotation might be in the right, so we should check to the right of mid.
                    low = mid+1;
            }else { //If left portion is rotated, then it means right portion is regular ascending.
                if(target > nums[mid] && target <= nums[high]) //If target is to the right of mid(in the regular portion).
                    low = mid+1;
                else //If not, check the left portion, which is the rotated portion.
                    high = mid-1;
            }
        }
        return -1;
    }
}
