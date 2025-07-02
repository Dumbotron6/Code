package arrays;

public class SubArraysWithOddSums {
    /*
    https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
     */

    public int numOfSubarrays(int[] arr) {
        int currSum = 0;
        int oddSums = 0;
        int evenSums = 0;
        int total = 0;
        long mod = (long) 1e9 + 7;

        /*
        Why keep track of even and odd sums? At any point, if the sum is odd, any even sum prior would still result in odd.
        Similar case for even sum. If the sum is even, then any odd prior sum would result in odd.
        So, for any current odd sum, the total would be that plus number of even sums prior.
        For any current even sum, the total would be number of odd sums prior.
        */
        for(int ele : arr) {
            currSum += ele;
            if(currSum%2 == 1) {
                total = (int)((total+evenSums+1)%mod);
                oddSums++;
            }else {
                total = (int)((total+oddSums)%mod);
                evenSums++;
            }
        }

        return total;
    }
}
