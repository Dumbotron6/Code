package arrays;

import java.util.List;

public class AdjacentIncSubarray1 {
    /*
    https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/
     */

    /*
    Why check doubleLen? Consider [1,2,3,4,5,6,7,8] and [1,2,3,4,5,6,5,6,7,8].
    In the first one, 1-4 and 5-8 satisfy. But in the second, 3-6 and 5-8 satisfy.
    In the second case, we don't know where len1 ends until we encounter a len2 starting point.
    So count all increasing elements in len1, if len1 satisfies 2*k, then we have two arrays.
    Or as in the second example when len1 is 6 and len2 is 4, then we have two arrays.
    That's why we have the return true statement as it is below.
    */
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        boolean foundOne = false;
        int doubleLen = 2*k;
        int len1 = 1;
        int len2 = 0;

        for(int i = 1; i < nums.size(); i++) {
            if(nums.get(i) > nums.get(i-1)) {
                if(len2 == 0) {
                    len1++;
                }else {
                    len2++;
                }
            }else if(len1 >= k && len2 == 0){
                len2 = 1;
            }else {
                len1 = 1;
                len2 = 0;
            }

            if(len1 == doubleLen || (len1 >= k && len2 == k)) {
                return true;
            }
        }

        return false;
    }
}
