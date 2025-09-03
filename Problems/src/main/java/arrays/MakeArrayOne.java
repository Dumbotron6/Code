package arrays;

public class MakeArrayOne {
    /*
    https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/description/
     */

    public int minOperations(int[] nums) {
        int len = nums.length;
        int ops = 0;

        for(int i = 0; i < len-2; i++) {
            if(nums[i] == 0) {
                flip(nums, i);
                flip(nums, i+1);
                flip(nums, i+2);
                ops++;
            }
        }

        if(nums[len-1] == 1 && nums[len-2] == 1 && nums[len-3] == 1) {
            return ops;
        }

        return -1;
    }

    public void flip(int[] nums, int pos) {
        if(nums[pos] == 1) {
            nums[pos] = 0;
        }else {
            nums[pos] = 1;
        }
    }
}
