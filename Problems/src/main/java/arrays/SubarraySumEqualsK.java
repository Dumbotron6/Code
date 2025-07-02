package arrays;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    /*
    https://leetcode.com/problems/subarray-sum-equals-k/description/
     */

    /*
    Solution uses prefix sum- we store value of sum of previous elements.
    Here we store prefix sum and it's count ie. number of occurrences.
    In an array [1,2,3,-3,1,1,1,4,-3] and k = 3, we store sum values of
        [1,3,6,3,4,5,6,10,7]. Here, 1 occurs once, 3 twice etc.
    Now we when we have a sum say 5, we need to look for if 2(5-3 ie sum-k) occurred previously ie the count.
    If 2 occurred twice, then it means there exists two arrays post which we get 3 as sum.
    If we take above array as example, k = 3. So on each iteration, we need to look for sum-k value.
    If sum = 6, then we need to look for occurrences of 6-3 values.
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumSet = new HashMap<Integer, Integer>();

        int sum = 0;
        int count = 0;
        /*
        We add 0 in the map as when sum == k, we will get sum-k = 0 which wouldn't have come up before.
        It can be thought of as- there will always exist a 0 sum before we begin.
         */
        sumSet.put(0,1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if(sumSet.containsKey(sum-k))
                count += sumSet.get(sum-k);

            if(!sumSet.containsKey(sum))
                sumSet.put(sum, 1);
            else
                sumSet.put(sum, sumSet.get(sum)+1);
        }
        return count;
    }

}
