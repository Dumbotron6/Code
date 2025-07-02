package arrays;

public class ZeroArrayTransformation2 {
    /*
    https://leetcode.com/problems/zero-array-transformation-ii/
     */

    public int minZeroArray(int[] nums, int[][] queries) {
        //We use 'Sweep line' aka 'Difference array' to increment value in ranges.
        int[] sweepTrack = new int[nums.length+1];
        int i = 0;
        int steps = 0;
        int sum = 0;
        int len = nums.length;

        //Edge case. If all or some initial elements are already 0, we don't need to do anything. So skip over them.
        while(i < len && nums[i] == 0) {
            i++;
        }
        if(i == len) {
            return steps;
        }

        /*
        The first if condition is to skip over the ranges we don't need, ie, ranges ending before i.
        When we encounter a range that i falls between, add that val to the sum. That is the reason for the if(between) condition.
        This is so we don't miss any increments before index i.
        Why query[0] < i ? query[0] == i is already covered by sweepTrack[query[0]] += query[2];
        The while loop is to skip over all elements where sum becomes greater, as any increments to them in further queries is useless to us.
        */
        for(int[] query : queries) {
            if(query[1] >= i) {
                sweepTrack[query[0]] += query[2];
                sweepTrack[query[1]+1] -= query[2];
            }
            if(query[0] < i && query[1] >= i) {
                sum += query[2];
            }
            while(i < len && sum + sweepTrack[i] >= nums[i]) {
                sum+=sweepTrack[i];
                i++;
            }
            steps++;
            if(i == len) {
                return steps;
            }
        }
        return -1;
    }
}
