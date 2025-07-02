package arrays;

public class MinimumOperationsToMakeDistinct {

    /*
    https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/
     */

    public int minimumOperations(int[] nums) {
        int[] counts = new int[101];
        int ops = 0;
        int i = 0, j = 0;
        while(i < nums.length) {
            counts[nums[i]]++;
            while(counts[nums[i]] > 1) {
                int ctr = 0;
                while(j < nums.length && ctr < 3) {
                    counts[nums[j]]--;
                    ctr++;
                    j++;
                }
                ops++;
            }
            i++;
        }

        return ops;
    }

    public int minimumOperationsAlt(int[] nums) {
        boolean[] seen = new boolean[101];
        for(int i = nums.length-1; i >= 0; i--) {
            if(seen[nums[i]]) {
                return (i/3)+1;
            }
            seen[nums[i]] = true;
        }

        return 0;
    }
}
