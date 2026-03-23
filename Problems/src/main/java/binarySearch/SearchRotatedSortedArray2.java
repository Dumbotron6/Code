package binarySearch;

public class SearchRotatedSortedArray2 {
    /*
    https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
     */

    /*
    Almost same logic as SearchRotatedSortedArray. The difference is that we have to handle duplicates here.
    The condition checks if left, mid and right are equal. If so, left and right keep shrinking till it's not.
    What is only left == mid or right == mid?
    That will be handled by the regular binary search conditions below. Say left == mid,
        depending on whether target is smaller or greater, right or left will move.
     */
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if(nums[mid] == target)
                return true;

            if(nums[mid] == nums[high] && nums[mid] == nums[low]) {
                low++;
                high--;
                continue;
            }

            if(nums[low] <= nums[mid]) {
                if(target >= nums[low] && target < nums[mid])
                    high = mid-1;
                else
                    low = mid+1;
            }else {
                if(target > nums[mid] && target <= nums[high])
                    low = mid+1;
                else
                    high = mid-1;
            }
        }
        return false;
    }
}
