package greedy;

public class MinIncrementsToTarget {
    /*
    https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
     */

    /*
    It's only 1 increment per step. So if we pick a subarray, the max element will be the minimum increments we have to do.
    Why increment only when num greater than prev? Because we have already done prev increments. Now the difference has to be added.
    What if a lesser number comes after? That is also covered as we already incremented to a higher number before.
    Whenever we see a dip and a rise(aka valley and hill), anything after the dip will be a new subarray.
    For example, take [3,2,3,4]. At 2, the steps will be 3 but any increments we have done so far would have only partially covered 3 and 4.
    Specifically, after 2 increments, the array would become [2,2,2,2]
        and we would have to increment index 0 as one subarray and indexes 2 and 3 as another subarray.
    So any rise after a dip will become a separate subarray.
    */
    public int minNumberOperations(int[] target) {
        int steps = 0;
        int prev = 0;

        for(int num : target) {
            if(num > prev) {
                steps += num-prev;
            }
            prev = num;
        }

        return steps;
    }
}
