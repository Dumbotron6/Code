package arrays;

public class ZeroArrayTransformation1 {
    /*
    https://leetcode.com/problems/zero-array-transformation-i/description/
     */

    public boolean isZeroArray(int[] nums, int[][] queries) {
        /*
        What we do here is a technique called 'Sweep line' or 'Difference array'.
        By doing +1 on the beginning index, we know that a range started here.
        By doing -1 on the end index+1, we know that range ended in the previous index, so we should stop adding 1 from this index onwards.
        Now take array [1,0,1] and query [1,2]. The sweepTrack would be [1,0,0,-1].
        So we need to add 1 in index 0 and keep that for subsequent indexes till we encounter a -1.
        If the array is [1,2,3,4,5] and the queries are [0,1],[0,2],[1,2], the sweepTrack would be [2,1,-1,-2,0,0].
        What toes this mean? If we do the prefixSums, then we would end up with [2,3,2,0,0] which is exactly what the range gives.
        */
        int[] sweepTrack = new int[nums.length+1];

        for(int[] query : queries) {
            sweepTrack[query[0]]++;
            sweepTrack[query[1]+1]--;
        }
        int tmp = 0;

        for(int i = 0; i < nums.length; i++) {
            tmp += sweepTrack[i];
            if(tmp < nums[i]) {
                return false;
            }
        }

        return true;
    }
}
