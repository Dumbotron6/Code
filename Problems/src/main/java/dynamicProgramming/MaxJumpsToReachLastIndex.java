package dynamicProgramming;

public class MaxJumpsToReachLastIndex {
    /*
    https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/description/
     */

    /*
    At each point, we check if that point can be reached from any previous point.
    We store the max steps it can take to reach that point using dp.
    Why maxJumps[0] = 1? Because of maxJumps[j] > 0 check. If we pass j==3 while i==4, j==3 has 0, it means index 3 was impossible to reach.
    At that point, we cannot assing maxJumps[i] = Math.max(maxJumps[j]+1, maxJumps[i]), because j == 3 should not be check even if it satisfies target.
    But for maxJumps[0]==0 it would be skipped over. Hence we assign it as 1 and while returning, we return result-1;
    */
    public int maximumJumps(int[] nums, int target) {
        int len = nums.length;
        int[] maxJumps = new int[len];
        maxJumps[0] = 1;

        for(int i = 1; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(maxJumps[j] > 0 && Math.abs(nums[i]-nums[j]) <= target) {
                    maxJumps[i] = Math.max(maxJumps[j]+1, maxJumps[i]);
                }
            }
        }

        return maxJumps[len-1] > 0 ? maxJumps[len-1]-1 : -1;
    }
}
