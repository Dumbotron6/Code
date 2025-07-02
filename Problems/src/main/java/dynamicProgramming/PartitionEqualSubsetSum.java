package dynamicProgramming;

public class PartitionEqualSubsetSum {
    /*
    https://leetcode.com/problems/partition-equal-subset-sum/
     */

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num : nums) {
            totalSum += num;
        }

        if(totalSum%2 != 0) { //Is sum is odd, it cannot be split into equal two halves.
            return false;
        }

        //Why do this? If any combination in the array matches half, then the other half will automatically be equal.
        totalSum /= 2;
        Boolean[][] tracker = new Boolean[nums.length][totalSum+1];
        return checkRecur(nums, totalSum, 0, tracker);
    }

    /*
    We make use recursion to check if the current combo matches sum (we do in reverse so we check currSum == 0).
    Since a sum can be reached any number of ways at a point, we keep track of that position using memoizations,
        so see if from the current sum and i position, the rest of the sum can be obtained.
    This avoids redundant calls.
    Better solution below.
    */
    public boolean checkRecur(int[] nums, int currSum, int ptr, Boolean[][] tracker) {
        if(ptr >= nums.length || currSum < 0) {
            return false;
        }
        if(currSum == 0) {
            return true;
        }

        if(tracker[ptr][currSum] != null ) {
            return tracker[ptr][currSum];
        }

        for(int i = ptr; i < nums.length; i++) {
            tracker[ptr][currSum] = checkRecur(nums, currSum-nums[i], i+1, tracker);
            if(tracker[ptr][currSum] == true) {
                return true;
            }
        }
        return tracker[ptr][currSum];
    }

    /*
    Building on the previous solution, at each step, we have two options. Either add nums[ptr] to currSum (subtract since we go in reverse)
        or not add it. So that's what we do with the || condition, check if either one of them sums up to total.
    This takes the bottom up approach, same as above.
    Space optimized solution is below this.
    */
    public boolean checkRecurBetter(int[] nums, int currSum, int ptr, Boolean[][] tracker) {
        if(ptr >= nums.length || currSum < 0) {
            return false;
        }
        if(currSum == 0) {
            return true;
        }

        if(tracker[ptr][currSum] != null ) {
            return tracker[ptr][currSum];
        }

        tracker[ptr][currSum] = checkRecurBetter(nums, currSum-nums[ptr], ptr+1, tracker) || checkRecurBetter(nums, currSum, ptr+1, tracker);

        return tracker[ptr][currSum];
    }

    public boolean canPartitionSpaceOptimal(int[] nums) {
        int totalSum = 0;
        for(int num : nums) {
            totalSum += num;
        }

        if(totalSum%2 != 0) { //Is sum is odd, it cannot be split into equal two halves.
            return false;
        }

        //Why do this? If any combination in the array matches half, then the other half will automatically be equal.
        totalSum /= 2;

        /*
        Build on the previous submitted solution. Now have a target(totalSum).
        We need to check if a combination of the elements can sum up to target.
        This is almost similar to the coin change solution, only without repeating the coins ie, we can use the coin only once.
        Because of that, we can have a boolean dp instead of int dp. Because only dp[num] will change at most, not dp[2*num] etc.
        Why go in reverse? That is what avoids repetition. Checking from the back, take [1,2,1,4]. TotalSum will be 4.
        Now when 1 comes, the array will be [true,true,false,false,false]. This means, if any other number reaches 1,
            then it can reach 0 from there. If we went from front, the result would've been [true,true,true,true,true] as it would've
            repeated 1.
        */
        boolean[] dp = new boolean[totalSum+1];
        dp[0] = true;

        for(int num : nums) {
            for(int i = totalSum; i >= num; i--) {
                dp[i] = dp[i] || dp[i-num];
            }
        }
        return dp[totalSum];
    }

}
