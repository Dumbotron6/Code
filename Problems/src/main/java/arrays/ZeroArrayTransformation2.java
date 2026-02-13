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
                sweepTrack[query[0]] += query[2]; //If query[0] is less than i, it doesn't matter as we won't use it. That is what the below if is for.
                sweepTrack[query[1]+1] -= query[2];
            }
            if(query[0] < i && query[1] >= i) { //If i is 2 and query[0] is 0, we need to add query[2] to i. That's what this is for.
                sum += query[2];
            } //Only add if i falls within range, since we can't add something that won't cover i.
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
