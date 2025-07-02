package greedy;

public class MinimumEqualSum {
    /*
    https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/
     */

    public long minSum(int[] nums1, int[] nums2) {
        int zero1 = 0;
        int zero2 = 0;
        long sum1 = 0;
        long sum2 = 0;

        //Count 0s and the sum.
        for(int num : nums1) {
            if(num == 0) {
                zero1++;
            }else {
                sum1 += num;
            }
        }
        for(int num : nums2) {
            if(num == 0) {
                zero2++;
            }else {
                sum2 += num;
            }
        }

        //Replace all but one 0s with 1 and add to sum, which we can replace with any positive int to match sum1 and sum2.
        if(zero1 > 1) {
            sum1 += zero1-1;
            zero1 = 1;
        }
        if(zero2 > 1) {
            sum2 += zero2-1;
            zero2 = 1;
        }

        //Essentially (zero1 == 0 && zero2 ==1) and (zero2 == 0 && zero1 == 1).
        if((sum2 >= sum1 && zero1 < zero2) || (sum1 >= sum2 && zero2 < zero1)) {
            return -1;
        }
        if(zero1 == 0 && zero2 == 0 && sum1 != sum2) {
            return -1;
        }

        //Now it's sum1+x == sum2+y. We don't really need to find x and y. We just have to get max+1;
        if(zero1 > 0 && zero2 > 0) {
            return Math.max(sum2,sum1)+1;
        }
        return Math.max(sum2,sum1);
    }
}
