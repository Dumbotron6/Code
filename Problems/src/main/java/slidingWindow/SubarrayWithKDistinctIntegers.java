package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class SubarrayWithKDistinctIntegers {
    /*
    https://leetcode.com/problems/subarrays-with-k-different-integers/
     */

    /*
    We are using a sliding window.
    We use the window to find all subarrays with distinct elements less than k.
    Then we use the window to find all subarrays with distinct elements less than k-1.
    Subtracting the two counts give us subarrays with distinct elements equal to k.
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        int kDistinct = findSubArrays(nums, k);
        int kDistinctMinus = findSubArrays(nums, k-1);

        return kDistinct - kDistinctMinus;
    }

    public int findSubArrays(int[] nums, int k) {
        Map<Integer, Integer> tracker = new HashMap<Integer, Integer>();
        int begin = 0;
        int end = 0;
        int total = 0;
        while(end < nums.length) {
            if(tracker.containsKey(nums[end]))
                tracker.put(nums[end], tracker.get(nums[end])+1);
            else
                tracker.put(nums[end], 1);
            while(tracker.size() > k) {
                if(tracker.get(nums[begin]) == 1)
                    tracker.remove(nums[begin]);
                else
                    tracker.put(nums[begin], tracker.get(nums[begin])-1);
                begin++;
            }
            total += (end-begin+1);
            end++;
        }
        return total;
    }
}
