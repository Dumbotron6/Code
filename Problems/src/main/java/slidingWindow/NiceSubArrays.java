package slidingWindow;

public class NiceSubArrays {
    /*
    https://leetcode.com/problems/count-number-of-nice-subarrays/
     */

    /*
    Same as BinarySubarrayWithSum. Instead of 0 and 1, we have even and odd.
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int sum = findSum(nums, k);
        int prevSum = findSum(nums, k-1);

        return sum-prevSum;
    }

    public int findSum(int[] nums, int k) {
        if(k < 0)
            return 0;
        int begin = 0;
        int end = 0;
        int sum = 0;
        int total = 0;

        while(end < nums.length) {
            sum += (nums[end]%2 == 1 ? 1 : 0);

            while(sum > k) {
                sum -= (nums[begin]%2 == 1 ? 1 : 0);
                begin++;
            }
            total += (end-begin+1);
            end++;
        }
        return total;
    }
}
