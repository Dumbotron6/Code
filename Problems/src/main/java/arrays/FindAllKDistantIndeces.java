package arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAllKDistantIndeces {
    /*
    https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
     */

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int right = 0;
        int left = 0;
        List<Integer> result = new ArrayList<Integer>();

        //At every key, sweep till right+k using left and add the index to result. Math.max ensures we don't duplicate add and also start at 0.
        while(right < nums.length) {
            if(nums[right] == key) {
                left = Math.max(left, right-k);
                while(left < nums.length && left <= right+k) {
                    result.add(left++);
                }
            }
            right++;
        }

        return result;
    }
}
