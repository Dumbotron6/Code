package arrays;

import java.util.List;

public class AdjacentIncSubarray2 {
    /*
    https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/
     */

    /*
    Building on AdjacentIncSubarray1, we use len1 and len2 to keep track of length of two adjacent increasing arrays.
    The first if(len2 == 0) means increase len1 only when no second array encountered yet.
    The second if(len2 == 0) means we are encountering the first element of len2 after len1.
    The else condition means if a second drop(non-increasing element) is encountered, then reset len1 and len2.
    We can encounter one increasing array, but that can also be divided into two equal arrays. Ex. [1,2,3,4,5,6,4].
    That is why we do Math.max(max, len1/2).
    if(len2 > len1) is because the second array can have the max increasing array. Ex. [8,-4,-1,16,20]
    */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int max = 1;
        int len1 = 1;
        int len2 = 0;

        int i = 1;

        while(i < nums.size()) {
            if(nums.get(i) > nums.get(i-1)) {
                if(len2 == 0) {
                    len1++;
                }else {
                    len2++;
                }
            }else {
                if(len2 == 0) {
                    len2++;
                }else {
                    len1 = 1;
                    len2 = 0;
                }
            }
            if(len2 > len1) {
                len1 = len2;
                len2 = 0;
            }
            i++;
            max = Math.max(max, len1/2);
            max = Math.max(max, Math.min(len1, len2));
        }

        return max;
    }

    //Same approach but removes unnecessary steps. Compare and revise.
    public int maxIncreasingSubarraysAlt(List<Integer> nums) {
        int max = 1;
        int len1 = 1;
        int len2 = 0;

        int i = 1;

        while(i < nums.size()) {
            if(nums.get(i) > nums.get(i-1)) {
                len1++;
            }else {
                len2 = len1;
                len1 = 1;
            }
            i++;
            max = Math.max(max, len1/2);
            max = Math.max(max, Math.min(len1, len2));
        }

        return max;
    }
}
