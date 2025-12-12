package arrays;

import java.util.Arrays;

public class CountParitionsEvenSum {
    /*
    https://leetcode.com/problems/count-partitions-with-even-sum-difference/
     */

    public int countPartitions(int[] nums) {
        int leftSum = 0;
        int rightSum = Arrays.stream(nums).sum();

        int partition = 0;
        for(int i = 0; i < nums.length-1; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];

            if((rightSum-leftSum)%2 == 0) {
                partition++;
            }
        }

        return partition;
    }
}
