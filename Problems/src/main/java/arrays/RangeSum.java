package arrays;

public class RangeSum {
    /*
    https://leetcode.com/problems/range-sum-query-immutable/description/
     */

    class NumArray {
        int[] prefixSum;

        public NumArray(int[] nums) {
            //Why +1? If we get left as 0, easier to start with prefixSum[0] as 0. So value will be [right+1] - [left] as left is always behind 1.
            prefixSum = new int[nums.length+1];
            int sum = 0;
            for(int i = 1; i < prefixSum.length; i++) {
                sum += nums[i-1];
                prefixSum[i] = sum;
            }
        }

        public int sumRange(int left, int right) {
            return prefixSum[right+1] - prefixSum[left];
        }
    }
}
