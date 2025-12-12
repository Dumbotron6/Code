package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DivideArrayKSets {
    /*
    https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
     */

    //Same as HandOfStraights but this is better as we don't have to use heap which increases time complexity.
    public boolean isPossibleDivide(int[] nums, int k) {
        Arrays.sort(nums); //Sorting helps us iterate strictly forward ie, check num+1, num+2 etc.
        Map<Integer, Integer> numCount = new HashMap<Integer, Integer>();

        for(int num : nums) {
            numCount.put(num, numCount.getOrDefault(num, 0)+1);
        }

        for(int num : nums) {
            //We maintain at least 0, so we don't have to check 1 count and remove from map every time.
            //We also see if num is not already used up.
            if(numCount.get(num) > 0) {
                for(int i = 0; i < k; i++) {
                    int val = num+i;
                    if(numCount.containsKey(val) && numCount.get(val) > 0) { //Subsequent number is present and not already used.
                        numCount.put(val, numCount.get(val)-1);
                    }else {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
