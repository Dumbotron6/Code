package greedy;

public class JumpGame2 {
    /*
    https://leetcode.com/problems/jump-game-ii/description/
     */


    /*
    To calculate the minimum number of jumps, we need to calculate the maxJump distance.
    So for every index, we calculate the max distance and then try out all combinations between the index
        and the max possible distance.
    The idea is, we get a max distance, set that as the range, and then obtain the maximum distance possible
        from that range. This way of picking max possible distance at each range gives us minimum jumps.
    For [1,2,3,1,2,0,5,6], the index is 0 and the max distance becomes 1.
    Now left index is 1 and the right is also 1. In the next step, the max distance becomes
        3(as max jump from index 1 is 2). So left index becomes 2(as we need to check from the previous right index)
        and the right index becomes 3.
    Iterating from left index to right index again allows us to pick the maximum distance that can be obtained
        in that range.
     */
    public int jump(int[] nums) {
        int jumpMaxLeft = 0;
        int jumpMaxRight = 0;
        int jumpCount = 0;

        while(jumpMaxRight < nums.length - 1) {
            int maxDistance = 0;

            for(int i = jumpMaxLeft; i <= jumpMaxRight; i++) {
                maxDistance = Math.max(maxDistance, i + nums[i]);
            }
            jumpMaxLeft = jumpMaxRight + 1;
            jumpMaxRight = maxDistance;
            jumpCount++;
        }

        return jumpCount;
    }
}
