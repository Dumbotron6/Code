package greedy;

import java.util.Arrays;

public class LongestSubsequenceLimitedSum {
    /*
    https://leetcode.com/problems/longest-subsequence-with-limited-sum/description/
     */

    /*
    Remember, for subsequence problems like these, order does not matter since we only need the count.
    So we can sort it without worry. Prefix sum helps us keep track of sum from low to high.
    We can then binary search that sum to determine the answer.
    */
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] prefixSum = new int[nums.length];
        int[] answer = new int[queries.length];
        prefixSum[0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            prefixSum[i] = nums[i] + prefixSum[i-1];
        }

        for(int i = 0; i < queries.length; i++) {
            answer[i] = binarySearch(prefixSum, queries[i]);
        }

        return answer;
    }

    public int binarySearch(int[] prefixSum, int query) {
        int left = 0, right = prefixSum.length-1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            if(prefixSum[mid] > query) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        //Why right+1? right is the index. So index 0 means 1 element.
        return right+1;
    }
}
