package arrays;

public class CountHillsAndVallies {
    /*
    https://leetcode.com/problems/count-hills-and-valleys-in-an-array/
     */

    public int countHillValley(int[] nums) {
        int total = 0;
        int left = 0;
        int right = 2;
        int pos = 1;

        /*
        Move left only when peak or valley found. If it keeps rising or dipping or remains equal, don't move left.
        Why do this? Try exampls [3,2,1] or [1,2,3] or [1,2,2,1].
        */
        while(right < nums.length) {
            if(nums[pos] < nums[left] && nums[pos] < nums[right]) {//Valley.
                total++;
                left = pos;
            }else if(nums[pos] > nums[left] && nums[pos] > nums[right]) {//Peak.
                total++;
                left = pos;
            }
            right++;
            pos++;
        }

        return total;
    }
}
