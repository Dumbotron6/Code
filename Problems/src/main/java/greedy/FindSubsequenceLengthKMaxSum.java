package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FindSubsequenceLengthKMaxSum {
    /*
    https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
     */

    //Instead of picking and sorting like below, we can also use PriorityQueue.
    public int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        int[][] indexMap = new int[len][2];
        int[][] kMap = new int[k][2];

        //Store the number and it's index.
        for(int i = 0; i < len; i++) {
            indexMap[i][0] = nums[i];
            indexMap[i][1] = i;
        }
        Arrays.sort(indexMap, Comparator.comparingInt(a -> a[0])); //Sort by value.
        //Above is same as Arrays.sort(indexMap, (a,b) -> a[0]-b[0]);

        //Store max k numbers.
        for(int i = 0; i < k; i++) {
            kMap[i][0] = indexMap[len-k+i][0];
            kMap[i][1] = indexMap[len-k+i][1];
        }
        Arrays.sort(kMap, Comparator.comparingInt(a -> a[1])); //Sort the picked max numbers by index.

        int[] result = new int[k];

        //Put the final numbers in an array and return.
        for(int i = 0; i < k; i++) {
            result[i] = kMap[i][0];
        }

        return result;
    }
}
