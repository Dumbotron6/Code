package arrays;

public class SortColors {
    /*
    https://leetcode.com/problems/sort-colors/description/
     */

    /*
    We take the two pointer approach. One from left and one from right.
    Consider the array [2,0,1]. When i is at 0th position, nothing happens. When at 1st, then nums[i] < nums[low].
    The swap happens. Now i remains at 1st. Array becomes [0,2,1]. In the next iteration, nums[i] > nums[high].
        So swap happens.
    The reason we do low++ and high-- at below conditions are because 0 and 1 are supposed to be placed at the beginning
        and end anyway.
     */
    public void sortColors(int[] nums) {

        int low = 0;
        int high = nums.length - 1;
        int i = low;

        while(i <= high) {
            if(nums[i] < nums[low]) {
                int temp = nums[low];
                nums[low] = nums[i];
                nums[i] = temp;
            } else if(nums[i] > nums[high]) {
                int temp = nums[high];
                nums[high] = nums[i];
                nums[i] = temp;
            } else
                i++;
            if(nums[low] == 0) {
                low++;
                i = low;
            }
            if(nums[high] == 2)
                high--;
        }

    }
}
