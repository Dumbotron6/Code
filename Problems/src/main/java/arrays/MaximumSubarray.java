package arrays;

public class MaximumSubarray {
    /*
    https://leetcode.com/problems/maximum-subarray/
     */

    /*
    This is also known as Kadane’s Algorithm.
    Sum > high is straightforward. But why if(sum < 0) sum = 0?
    Because at any point if sum becomes less than zero, say [2,-3,4], 4 will always be greater than whatever comes before.
        ie, 4 will always be greater than -1(2-3).
    As long as sum remains positive, anything that adds to it will be higher than individual ele.
     */
    public int maxSubArray(int[] nums) {

        int sum = 0;
        int high = Integer.MIN_VALUE;

        for(int ele : nums) {
            sum += ele;
            if(sum > high)
                high = sum;
            if(sum < 0)
                sum = 0;
        }

        return high;

    }
}
