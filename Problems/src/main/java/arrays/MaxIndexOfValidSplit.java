package arrays;

import java.util.*;

public class MaxIndexOfValidSplit {
    /*
    https://leetcode.com/problems/minimum-index-of-a-valid-split/
     */

    public int minimumIndex(List<Integer> nums) {
        /*
        Get the max occuring element and it's count.
        If it needs to be more than half in both left and tight, then the count has to be atleast greater than n/2.
        */
        int[] maxEle = new int[2];
        Map<Integer, Integer> countMapper = new HashMap<Integer, Integer>();
        for(int num : nums) {
            countMapper.put(num, countMapper.getOrDefault(num, 0)+1);
            if(maxEle[1] < countMapper.get(num)) {
                maxEle[0] = num;
                maxEle[1] = countMapper.get(num);
            }
        }
        int n = nums.size();
        if(maxEle[1] <= n/2) {
            return -1;
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            //Check the count till now.
            if(nums.get(i) == maxEle[0]) {
                count++;
            }
            /*
            Check if it is dominant on both left and right.
            Why (i+1)? Take [1,2,2,2]. At any i, the number of elements is i+1.
            maxEle[1]-count is the remaining count.
            (n-i-1)? In the above array, when i is 1, n-i is 3 while the actual remaining elements are 2 so n-i-1.
            */
            if(count > (i+1)/2 && maxEle[1]-count > (n-i-1)/2) {
                return i;
            }
        }
        return -1;
    }
    /*
    NOTE: Instead of count and calculating maxEle[1]-count, we can keep left and right. Increment left and decrement
        right each time. Like below.
        int left = 0, right = maxEle[1];
        if(left > (i+1)/2 && right > (n-i-1)/2)

     We can also find the max occurring number using MajorityElement but it won't give us the count.
     To get count, we have to loop again to match numbers with the max element and count.
     It would give is O(1) space thought, instead of O(N) like the above solution.
     */
}
