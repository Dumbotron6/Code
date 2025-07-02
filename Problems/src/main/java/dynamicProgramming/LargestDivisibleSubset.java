package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    /*
    https://leetcode.com/problems/largest-divisible-subset/
     */

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer>[] dp = new List[nums.length];
        Arrays.sort(nums);
        List<Integer> returnList = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            List<Integer> tempList = findSubset(nums, i, dp);
            if(returnList.size() < tempList.size()) {
                returnList = tempList;
            }
        }
        return returnList;
    }

    /*
    Take array [7,14,21,42]. We can end up with two results, [7,14,42] and [7,21,42].
    That's what we identify and store in DP. Starting at any point, what is the max array we can obtain.
    So for above array, it will be [7,14,42],[14,42],[21,42],[42].
    Why returnList = new ArrayList<Integer>(tempList) ? Because returnList = tempList
        would result in any additions to this array result in it being added in previous recursive call which we don't want.
        ie, at 42, the max array is [42]. By direct assignment, it would end up being [14,21,42] because two recursive calls
        are made to 42, one from 14 and another from 21.
    */
    public List<Integer> findSubset(int[] nums, int ptr,  List<Integer>[] dp) {
        if(ptr == nums.length) {
            return new ArrayList<Integer>();
        }
        if(dp[ptr] != null) {
            return dp[ptr];
        }

        List<Integer> returnList = new ArrayList<Integer>();
        for(int i = ptr+1; i < nums.length; i++) {
            if(nums[i]%nums[ptr] == 0) {
                List<Integer> tempList = findSubset(nums, i, dp);
                if(returnList.size() < tempList.size()) {
                    returnList = new ArrayList<Integer>(tempList);
                }
            }
        }
        returnList.add(nums[ptr]);
        dp[ptr] = returnList;

        return dp[ptr];
    }
}
