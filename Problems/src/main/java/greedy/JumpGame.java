package greedy;

public class JumpGame {
    /*
    https://leetcode.com/problems/jump-game/description/
     */

    /*
    The logic is, we calculate maximum jump distance for ith place.
    If nums[i] becomes 0 and if we have a jump distance greater than i,
        it means any prior combination could lead to that place being jumped over.
    Ex. For [1,2,3,1,2,0,5,6], when we reach 0, i is 5. They max jump distance till that point would be 6.
    So for example if we take 1,2,3 as jumps or 1,2,2 as jumps we'd have overtaken 0.
    Which is the reason for jumpMax <= i check. In [1,1,0,1,1], the maxJump till 0 would be 2. We can't reach the end
        of the array with any jumps.
     */
    public boolean canJump(int[] nums) {

        int jumpMax = 0;

        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == 0 && jumpMax <= i) {
                return false;
            }
            jumpMax = Math.max(jumpMax, i + nums[i]);
        }

        return true;
    }
}
