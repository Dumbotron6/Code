package slidingWindow;

public class BinarySubarraysWithSum {
    /*
    https://leetcode.com/problems/binary-subarrays-with-sum/description/
     */

    /*
    This one is tricky.
    We calculate number of subarrays whose sum is <= goal. Then we calculate number of subarrays whose sum is <= goal-1.
    Then when we subtract both, ie (subGoals - subGoalsMinus1), we get number of subarrays == goal.
    Why? Consider nums [1,0,0,1,1,0] and goal 2. Usually, we'd shrink the sliding window when the sum becomes > goal.
    Here, after [0,0,1,1], it never becomes less than 0. Also, [0,0,1,1], [0,1,1], [0,1,1,0], [1,1], [0,0,1,1,0] all are valid.
    But how do we know when to shrink from the left since it never becomes > goal?
    Hence, we follow this approach.
     */
    public int numSubarraysWithSum(int[] nums, int goal) {

        int totalGoals = getGoalCount(nums, goal);
        int totalGoalsPrev = getGoalCount(nums, goal-1);

        return totalGoals-totalGoalsPrev;
    }

    /*
    Why totalGoal += end-begin+1? Say nums [1,0,0,1,1,0] and goal 2. We calculate arrays <= 2.
    Now if we are in the array [1,0,0,1], then [1],[0,1],[0,0,1],[1,0,0,1] are all valid. So 4 subarrays.
    Which is nothing but end-begin+1.
     */
    public int getGoalCount(int[] nums, int goal) {
        if(goal < 0)
            return 0;
        int begin = 0;
        int end = 0;
        int totalGoals = 0;
        int goalCount = 0;

        while(end < nums.length) {
            goalCount += nums[end];
            while(goalCount > goal) {
                goalCount -= nums[begin];
                begin++;
            }
            totalGoals += end-begin+1;
            end++;
        }

        return totalGoals;
    }
}
