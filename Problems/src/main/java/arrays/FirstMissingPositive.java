package arrays;

public class FirstMissingPositive {
    /*
    https://leetcode.com/problems/first-missing-positive/description/
     */

    //This is hard because we are supposed to use constant space and not a hashset.
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for(int i = 0; i < len; i++) { //We don't care about negatives, so turn all negatives to 0.
            if(nums[i] < 0) {
                nums[i] = 0;
            }
        }

        /*
        We know all positives should be between 1 and len ideally.
        So we can nums itself instead of a hashset to mark whether a positive has been encountered or not.
        If val is 3, we mark nums[3-1].
        */
        for(int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]); //Why abs? Because marked positives become negative.
            if(val > 0 && val <= len) {
                /*
                Mark positions of encountered positives to negative.
                Why check > 0? Because they could've already been marked due to a duplicate.
                */
                if(nums[val-1] > 0){
                    nums[val-1] *= -1;
                }else if(nums[val-1] == 0) { //Can't turn 0 negative. So do the next best thing. That is the reason for val <= len check above.
                    nums[val-1] = -len-1;
                }
            }
            //Since we eliminated all negatives in the first loop, now all positives and abs(negatives) are positions to be marked.
        }

        for(int i = 0; i < len; i++) { //If all positives encountered, every index should have negative value.
            if(nums[i] >= 0) {
                return i+1;
            }
        }

        return len+1; //If every positive was encountered, then the first missing positive is outside len.
    }

    /*
    NOTE: Instead of setting negatives to 0, if we had set to len+1, then a bit of complexity can be reduces in
            the second loop.
     */
}
