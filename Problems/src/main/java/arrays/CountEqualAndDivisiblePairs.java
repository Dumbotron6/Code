package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountEqualAndDivisiblePairs {
    /*
    https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
     */

    public int countPairs(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] == nums[j] && i*j % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    //Slightly slower as constraints are very less.
    public int countPairsAlt(int[] nums, int k) {
        Map<Integer, List<Integer>> numTracker = new HashMap<Integer, List<Integer>>();
        int count = 0;

        for(int i = 0; i < nums.length; i++) {
            if(!numTracker.containsKey(nums[i])) {
                numTracker.put(nums[i], new ArrayList<Integer>());
            }
            for(int j : numTracker.get(nums[i])) {
                if(i*j % k == 0) {
                    count++;
                }
            }
            numTracker.get(nums[i]).add(i);
        }

        return count;
    }
}
