package misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {
    /*
    https://leetcode.com/problems/longest-harmonious-subsequence/
     */

    /*
    We get the freqency of each number, then check if +1 exists for each number. If yes we calculate count.
    In a subsequence, since we are free to delete any element, the order doesn't matter here,
        as we need exactly(possible) +1 for each number. The number and the +1 can be scattered across the array.
    */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0)+1);
        }

        int maxHarmony = 0;

        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if(freqMap.containsKey(entry.getKey()+1)) {
                maxHarmony = Math.max(maxHarmony, entry.getValue()+freqMap.get(entry.getKey()+1));
            }
        }

        return maxHarmony;
    }

    /*
    Since we sort the array, we place the +1 numbers next to each other, we iterate, keep moving right.
    We move left only when the difference is greater than 1. Since left remains stationary as long as the difference <= 1,
        we can get number of all num and num+1, which will be the length of the subsequence.
    */
    public int findLHSBetter(int[] nums) {
        Arrays.sort(nums);

        int maxHarmony = 0, left = 0, right = 0;

        while(right < nums.length) {
            int diff = nums[right] - nums[left];
            if(diff == 1) {
                maxHarmony = Math.max(maxHarmony, right-left+1);
            }
            if(diff <= 1) {
                right++;
            }else {
                left++;
            }
        }

        return maxHarmony;
    }
}
