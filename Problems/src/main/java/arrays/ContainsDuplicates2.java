package arrays;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicates2 {
    /*
    https://leetcode.com/problems/contains-duplicate-ii/
     */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> tracker = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            if(tracker.containsKey(nums[i]) && i-tracker.get(nums[i]) <= k) {
                return true;
            }
            tracker.put(nums[i],i);
        }

        return false;
    }
}
